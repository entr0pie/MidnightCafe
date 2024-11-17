package tandera.hackerspace.midnightcafe.data.recipe.liked

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
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
                    }.map { it.toRecipe() }

                    LOGGER.i("Receitas recebidas: ${recipes}")
                    trySend(recipes).isSuccess
                }
            }
            awaitClose { listener.remove() }
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun like(recipe: Recipe): Flow<Unit> {
        return this.list()
            .flatMapLatest { likedRecipes ->
                val alreadyLiked = likedRecipes.any { one -> areEqual(recipe, one) }
                if (alreadyLiked) return@flatMapLatest flowOf()

                val recipeDto = RecipeDto.fromRecipe(recipe, this.idGenerator.generate())

                val document = this.likedRecipesCollection.document(recipeDto.id!!)
                document.set(recipeDto).await()

                return@flatMapLatest flowOf()
            }

    }

    override suspend fun unlike(recipe: Recipe): Flow<Unit> {
        TODO("Not yet implemented")
    }

    private companion object {
        val LOGGER = Logger.fromClass(FirebaseLikedRecipesDataSource::class)

        fun areEqual(left: Recipe, right: Recipe): Boolean {
            return left.title == right.title && left.score == right.score
        }
    }

}