package tandera.hackerspace.midnightcafe.data.recipe.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tandera.hackerspace.midnightcafe.data.getMidnightCafeDB
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.util.SecureIdGenerator

class RecipeFeedViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeFeedDao = getMidnightCafeDB(application).getRecipeDao()
    private val localSource = RoomRecipeFeedDataSource(recipeFeedDao)
    private val idGenerator = SecureIdGenerator()
    private val remoteSource = FirebaseRecipeDataSource(idGenerator)
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

    fun delete(recipe: Recipe) {
        viewModelScope.launch {
            recipeFeedRepository.delete(recipe).collect()
        }
    }

    fun create(recipe: Recipe) {
        viewModelScope.launch {
            recipeFeedRepository.create(recipe).collect()
        }
    }
}