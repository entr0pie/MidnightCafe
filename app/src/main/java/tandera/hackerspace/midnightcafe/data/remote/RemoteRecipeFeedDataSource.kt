package tandera.hackerspace.midnightcafe.data.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tandera.hackerspace.midnightcafe.data.Recipe
import tandera.hackerspace.midnightcafe.util.RECIPES

interface RemoteRecipeFeedDataSource {
    suspend fun list(): Flow<List<Recipe>>
    suspend fun create(recipe: Recipe): Flow<Recipe>
}

class FirebaseRecipeDataSource : RemoteRecipeFeedDataSource {
    override suspend fun list(): Flow<List<Recipe>> {
        return flow {
            delay(1000)
            emit(RECIPES)
        }
    }

    override suspend fun create(recipe: Recipe): Flow<Recipe> {
        return flow {
            delay(1000)
            emit(recipe)
        }
    }
}