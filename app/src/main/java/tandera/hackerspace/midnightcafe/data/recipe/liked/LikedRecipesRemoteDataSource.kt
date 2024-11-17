package tandera.hackerspace.midnightcafe.data.recipe.liked

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.data.recipe.feed.RecipeDto
import tandera.hackerspace.midnightcafe.util.IdGenerator
import tandera.hackerspace.midnightcafe.util.Logger

interface LikedRecipesRemoteDataSource {
    suspend fun list(): Flow<List<Recipe>>
    suspend fun like(recipe: Recipe): Flow<Unit>
    suspend fun unlike(recipe: Recipe): Flow<Unit>
}

class FirebaseLikedRecipesDataSource(
    private val idGenerator: IdGenerator,
) : LikedRecipesRemoteDataSource {
    private val firestore = FirebaseFirestore.getInstance()
    private val likedRecipesCollection = firestore.collection("liked-recipes")

    override suspend fun list(): Flow<List<Recipe>> {
        return callbackFlow {
            val listener = likedRecipesCollection.addSnapshotListener { data, error ->
                LOGGER.i("Receitas recebidas do Firebase! Quantidade: ${data?.size()}")

                if (error != null) {
                    LOGGER.e("Erro ao receber receitas do Firebase", error)
                    close(error)
                    return@addSnapshotListener
                }

                if (data != null) {
                    val recipes = data.documents.mapNotNull {
                        it.toObject(RecipeDto::class.java)
                    }

                    LOGGER.i("Receitas recebidas: ${recipes}")
                    trySend(recipes.map { it.toRecipe() }).isSuccess
                }
            }
            awaitClose { listener.remove() }
        }

    }
    
    override suspend fun like(recipe: Recipe): Flow<Unit> {
        val likedRecipes = likedRecipesCollection.get().await().documents.mapNotNull {
            it.toObject(RecipeDto::class.java)
        }

        return flow {
            val alreadyLiked = likedRecipes.any { one -> areEqual(recipe, one.toRecipe()) }
            if (alreadyLiked) return@flow emit(Unit)

            val recipeDto = RecipeDto.fromRecipe(recipe, idGenerator.generate())

            val document = likedRecipesCollection.document(recipeDto.id!!)
            document.set(recipeDto).await()

            return@flow emit(Unit)
        }

    }

    override suspend fun unlike(recipe: Recipe): Flow<Unit> {
        val likedRecipes = likedRecipesCollection.get().await().documents.mapNotNull {
            it.toObject(RecipeDto::class.java)
        }

        return flow {
            val likedRecipe =
                likedRecipes.firstOrNull { one -> areEqual(recipe, one.toRecipe()) }

            if (likedRecipe == null) return@flow emit(Unit)

            likedRecipesCollection.document(likedRecipe.id!!).delete().await()
            return@flow emit(Unit)
        }
    }

    private companion object {
        val LOGGER = Logger.fromClass(FirebaseLikedRecipesDataSource::class)

        fun areEqual(left: Recipe, right: Recipe): Boolean {
            return left.title == right.title && left.score == right.score
        }
    }

}