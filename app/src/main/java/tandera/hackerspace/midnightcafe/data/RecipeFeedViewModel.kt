package tandera.hackerspace.midnightcafe.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tandera.hackerspace.midnightcafe.data.local.RoomRecipeFeedDataSource
import tandera.hackerspace.midnightcafe.data.local.getMidnightCafeDB
import tandera.hackerspace.midnightcafe.data.remote.FirebaseRecipeDataSource

class RecipeFeedViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeFeedDao = getMidnightCafeDB(application).getRecipeDao();
    private val localSource = RoomRecipeFeedDataSource(recipeFeedDao);
    private val remoteSource = FirebaseRecipeDataSource()
    private val recipeFeedRepository = RecipeFeedRepositoryImpl(localSource, remoteSource)

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    init {
        fetchAvailableRecipes()
    }

    private fun fetchAvailableRecipes() {
        viewModelScope.launch {
            recipeFeedRepository.getFeed().collect { recipes ->
                _recipes.value = recipes
            }
        }
    }
}