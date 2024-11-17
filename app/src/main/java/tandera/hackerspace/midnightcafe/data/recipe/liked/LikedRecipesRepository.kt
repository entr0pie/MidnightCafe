package tandera.hackerspace.midnightcafe.data.recipe.liked

import kotlinx.coroutines.flow.Flow
import tandera.hackerspace.midnightcafe.data.recipe.Recipe

interface LikedRecipesRepository {
    suspend fun list(): Flow<List<Recipe>>
    suspend fun like(recipe: Recipe): Flow<Unit>
    suspend fun unlike(recipe: Recipe): Flow<Unit>
}

class LikedRecipesRepositoryImpl(
    private val remote: LikedRecipesRemoteDataSource,
) : LikedRecipesRepository {

    override suspend fun list(): Flow<List<Recipe>> = remote.list()

    override suspend fun like(recipe: Recipe): Flow<Unit> = remote.like(recipe)

    override suspend fun unlike(recipe: Recipe): Flow<Unit> = remote.unlike(recipe)

}