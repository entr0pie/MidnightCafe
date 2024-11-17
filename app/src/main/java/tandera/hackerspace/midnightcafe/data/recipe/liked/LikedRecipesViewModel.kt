package tandera.hackerspace.midnightcafe.data.recipe.liked

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.util.SecureIdGenerator

class LikedRecipesViewModel(private val application: Application) : AndroidViewModel(application) {
    private val idGenerator = SecureIdGenerator()
    private val remote = FirebaseLikedRecipesDataSource(idGenerator)
    private val repository = LikedRecipesRepositoryImpl(remote)

    private val _likedRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val likedRecipes: StateFlow<List<Recipe>> get() = _likedRecipes

    init {
        sync()
    }

    fun sync() {
        viewModelScope.launch {
            repository.list().collect { liked ->
                _likedRecipes.value = liked
            }
        }
    }

    fun like(recipe: Recipe) {
        viewModelScope.launch {
            repository.like(recipe).collect()
        }
    }

    fun unlike(recipe: Recipe) {
        viewModelScope.launch {
            repository.unlike(recipe).collect()
        }
    }
}