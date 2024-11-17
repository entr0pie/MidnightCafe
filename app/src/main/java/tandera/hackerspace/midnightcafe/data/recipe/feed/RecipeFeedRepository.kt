package tandera.hackerspace.midnightcafe.data.recipe.feed

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import tandera.hackerspace.midnightcafe.data.recipe.Recipe

interface RecipeFeedRepository {
    suspend fun getFeed(): Flow<List<Recipe>>
    suspend fun create(recipe: Recipe): Flow<Unit>
    suspend fun delete(recipe: Recipe): Flow<Unit>
}

class RecipeFeedRepositoryImpl(
    private val local: LocalRecipeFeedDataSource,
    private val remote: RemoteRecipeFeedDataSource,
) : RecipeFeedRepository {

    /**
     * Busca todas as receitas do feed.
     *
     * Obs: o Firebase já possui um modo offline para a listagem de documentos e,
     * portanto, não é necessário uso do LocalRecipeFeedDataSource.
     *
     * https://firebase.google.com/docs/firestore/manage-data/enable-offline
     *
     * @return
     */
    override suspend fun getFeed(): Flow<List<Recipe>> = remote.list()
    override suspend fun create(recipe: Recipe): Flow<Unit> {
        return remote.create(recipe).zip(local.insert(recipe)) { _, _ -> Unit }
    }

    override suspend fun delete(recipe: Recipe): Flow<Unit> = remote.delete(recipe)
}
