package tandera.hackerspace.midnightcafe.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onEach
import tandera.hackerspace.midnightcafe.data.local.LocalRecipeFeedDataSource
import tandera.hackerspace.midnightcafe.data.remote.RemoteRecipeFeedDataSource

interface RecipeFeedRepository {
    suspend fun getFeed(): Flow<List<Recipe>>
}

class RecipeFeedRepositoryImpl(
    private val local: LocalRecipeFeedDataSource,
    private val remote: RemoteRecipeFeedDataSource,
) : RecipeFeedRepository {

    override suspend fun getFeed(): Flow<List<Recipe>> {
        Log.d("recipe_feed", "Buscando receitas para o feed.")

        return remote.list()
            .onEach {
                Log.d("recipe_feed", "Encontrou receitas no banco remoto! $it")
                local.wipe()
                local.insert(it)
            }
            .catch {
                Log.e("recipe_feed", "Erro ocorreu ao buscar o feed remoto: ", it)
                Log.d("recipe_feed", "Usando banco de dados local para popular feed.")
                emitAll(local.list())
            }
    }
}
