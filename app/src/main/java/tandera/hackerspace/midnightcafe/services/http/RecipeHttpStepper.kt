package tandera.hackerspace.midnightcafe.services.http

import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.services.LoopStepper
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel

private val RECIPES = listOf(
    RecipeModel(
        "Red Velvet",
        Score.FIVE,
        R.drawable.red_velvet,
        "A sliced piece of Red Velvet."
    ),
    RecipeModel(
        "Tiramisu",
        Score.THREE,
        R.drawable.tiramisu,
        "A sliced piece of Tiramisu."
    ),
    RecipeModel(
        "Brigadeiro",
        Score.FIVE,
        R.drawable.brigadeiro,
        "A common brigadeiro."
    )
)

class RecipeHttpStepper() : LoopStepper<RecipeModel>(*RECIPES.toTypedArray()) {}