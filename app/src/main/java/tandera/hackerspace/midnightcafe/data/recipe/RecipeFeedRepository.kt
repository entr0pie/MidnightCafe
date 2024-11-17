package tandera.hackerspace.midnightcafe.data.recipe

import kotlinx.coroutines.flow.Flow
import tandera.hackerspace.midnightcafe.data.recipe.local.LocalRecipeFeedDataSource
import tandera.hackerspace.midnightcafe.data.recipe.remote.RemoteRecipeFeedDataSource

interface RecipeFeedRepository {
    suspend fun getFeed(): Flow<List<Recipe>>
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
}
