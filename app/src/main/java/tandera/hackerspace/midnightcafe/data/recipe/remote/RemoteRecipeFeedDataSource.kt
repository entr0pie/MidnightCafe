package tandera.hackerspace.midnightcafe.data.recipe.remote

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.util.Logger

interface RemoteRecipeFeedDataSource {
    suspend fun list(): Flow<List<Recipe>>
    suspend fun create(recipe: Recipe): Flow<Recipe>
}

class FirebaseRecipeDataSource : RemoteRecipeFeedDataSource {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("midnight-cafe")

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

    override suspend fun create(recipe: Recipe): Flow<Recipe> {
        return flow {
            delay(1000)
            emit(recipe)
        }
    }

    private companion object {
        val LOGGER = Logger.fromClass(FirebaseRecipeDataSource::class)
    }
}