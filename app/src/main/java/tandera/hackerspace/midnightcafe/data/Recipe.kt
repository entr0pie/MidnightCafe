package tandera.hackerspace.midnightcafe.data

import androidx.annotation.DrawableRes
import tandera.hackerspace.midnightcafe.ui.components.common.Score

data class Recipe(
    val title: String,
    val score: Score,
    @DrawableRes val image: Int,
    val imageDescription: String,
    val ingredients: String,
    val instructions: String,
)