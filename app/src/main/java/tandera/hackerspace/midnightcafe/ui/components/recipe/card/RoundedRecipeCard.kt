package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.ui.components.common.Score

@Composable
fun RoundedRecipeCard(
    title: String,
    score: Score,
    @DrawableRes image: Int,
    imageDescription: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    starSize: Dp = 12.dp,
) {
    RecipeCard(
        title,
        score,
        image,
        imageDescription,
        modifier = modifier.clip(RoundedCornerShape(20.dp)),
        titleSize = titleSize,
        starSize = starSize
    )
}
