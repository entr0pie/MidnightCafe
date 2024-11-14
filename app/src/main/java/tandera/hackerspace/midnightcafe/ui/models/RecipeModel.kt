package tandera.hackerspace.midnightcafe.ui.models

import androidx.annotation.DrawableRes
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.entities.RecipeEntity
import tandera.hackerspace.midnightcafe.ui.components.common.Score

data class RecipeModel(
    val title: String,
    val score: Score,
    @DrawableRes val image: Int,
    val imageDescription: String,
    val ingredients: String,
    val instructions: String,
) {
    fun toRecipeEntity(): RecipeEntity {
        return RecipeEntity(null, title, score.value(), ingredients, instructions)
    }

    companion object {
        private val RECIPE_IMAGES =
            listOf(R.drawable.tiramisu, R.drawable.brigadeiro, R.drawable.red_velvet)

        fun fromRecipeEntity(entity: RecipeEntity): RecipeModel {
            return RecipeModel(
                title = entity.title,
                score = Score.of(entity.score)!!,
                imageDescription = "A sample recipe",
                image = RECIPE_IMAGES.random(),
                ingredients = entity.ingredients,
                instructions = entity.instructions,
            )
        }
    }
}