package tandera.hackerspace.midnightcafe.data.recipe.feed

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import tandera.hackerspace.midnightcafe.data.recipe.Recipe

interface LocalRecipeFeedDataSource {
    suspend fun list(): Flow<List<Recipe>>
    suspend fun insert(recipe: Recipe): Flow<Recipe>
    suspend fun insert(recipes: List<Recipe>): Flow<List<Recipe>>
    suspend fun wipe(): Flow<Unit>
}

class RoomRecipeFeedDataSource(private val dao: RecipeFeedDao) : LocalRecipeFeedDataSource {
    override suspend fun list(): Flow<List<Recipe>> {
        return dao.list().transform { recipes -> recipes.map { it.toRecipe() } }
    }

    override suspend fun insert(recipe: Recipe): Flow<Recipe> {
        return flow {
            val entity = RecipeFeedEntity.fromRecipe(recipe)
            dao.save(entity)
            emit(recipe)
        }
    }

    override suspend fun insert(recipes: List<Recipe>): Flow<List<Recipe>> {
        return flow {
            recipes.map {
                val entity = RecipeFeedEntity.fromRecipe(it)
                dao.save(entity)
            }

            emit(recipes)
        }
    }

    override suspend fun wipe(): Flow<Unit> {
        return flow {
            dao.wipe()
            emit(Unit)
        }
    }
}