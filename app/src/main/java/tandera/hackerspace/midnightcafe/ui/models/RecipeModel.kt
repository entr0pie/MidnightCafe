package tandera.hackerspace.midnightcafe.ui.models

import androidx.annotation.DrawableRes
import tandera.hackerspace.midnightcafe.ui.components.common.Score

data class RecipeModel(
    val title: String,
    val score: Score,
    @DrawableRes val image: Int,
    val imageDescription: String,
) {
}