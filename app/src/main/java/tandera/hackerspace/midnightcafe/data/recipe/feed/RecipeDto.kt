package tandera.hackerspace.midnightcafe.data.recipe.feed

import com.google.firebase.firestore.DocumentId
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.ui.components.common.Score

private val RECIPE_IMAGES =
    listOf(R.drawable.tiramisu, R.drawable.brigadeiro, R.drawable.red_velvet)

data class RecipeDto(
    @DocumentId val id: String?,
    val title: String,
    val score: Int,
    val ingredients: String,
    val instructions: String,
) {

    constructor() : this(null, "", 0, "", "") {}

    fun toRecipe() = Recipe(
        title = this.title,
        score = Score.of(this.score)!!,
        imageDescription = "A sample recipe",
        image = RECIPE_IMAGES.random(),
        ingredients = this.ingredients,
        instructions = this.instructions,
    )

    companion object {
        fun fromRecipe(recipe: Recipe, id: String? = null) = RecipeDto(
            id,
            recipe.title,
            recipe.score.value(),
            recipe.ingredients,
            recipe.instructions
        )
    }
}