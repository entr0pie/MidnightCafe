package tandera.hackerspace.midnightcafe.data.recipe.feed

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.extensions.equalsTo
import tandera.hackerspace.midnightcafe.util.Logger

interface RemoteRecipeFeedDataSource {
    suspend fun list(): Flow<List<Recipe>>
    suspend fun delete(recipe: Recipe): Flow<Unit>
}

class FirebaseRecipeDataSource : RemoteRecipeFeedDataSource {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("recipe-feed")

    override suspend fun list(): Flow<List<Recipe>> {
        return callbackFlow {
            val listener = collection.addSnapshotListener { data, error ->
                LOGGER.i("Feed de receitas recebido do Firebase! Quantidade: ${data?.size()}")

                if (error != null) {
                    LOGGER.e("Um erro ocorreu ao receber feed de receitas do Firebase", error)
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

    override suspend fun delete(recipe: Recipe): Flow<Unit> {
        val recipes =
            collection.get().await().documents.mapNotNull {
                it.toObject(RecipeDto::class.java)
            }

        return flow {
            val recipeToDelete = recipes.firstOrNull { one -> one.toRecipe().equalsTo(recipe) }
            if (recipeToDelete == null) return@flow emit(Unit)

            collection.document(recipeToDelete.id!!).delete().await()
            return@flow emit(Unit)
        }
    }

    private companion object {
        val LOGGER = Logger.fromClass(FirebaseRecipeDataSource::class)
    }
}