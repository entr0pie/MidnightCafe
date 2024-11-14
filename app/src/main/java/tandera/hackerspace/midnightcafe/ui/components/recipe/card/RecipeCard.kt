package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun RecipeCard(
    title: String,
    score: Score,
    @DrawableRes image: Int,
    imageDescription: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    starSize: Dp = 12.dp,
) {
    Box(modifier = modifier) {
        RecipeImage(image, imageDescription)
        RecipeFade()
        RecipeFooter(
            title = title,
            score = score,
            titleSize = titleSize,
            starSize = starSize,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}


@Composable
fun RecipeCard(
    recipeModel: RecipeModel,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    starSize: Dp = 12.dp,
) {
    RecipeCard(
        title = recipeModel.title,
        score = recipeModel.score,
        image = recipeModel.image,
        imageDescription = recipeModel.imageDescription,
        modifier = modifier,
        titleSize = titleSize,
        starSize = starSize
    )
}

@Composable
fun SkeletonRecipeCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Palette.Lavender),
        contentAlignment = Alignment.Center  // Centers the content
    ) {
        Text(text = "Loading your recipes...")
    }
}


@Preview
@Composable
private fun SkeletonStateCardPreview() {
    SkeletonRecipeCard(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
    )
}

@Preview
@Composable
private fun RecipeCardPreview() {
    RecipeCard(
        "Red Velvet",
        Score.FIVE,
        R.drawable.red_velvet,
        "A sliced piece of Red Velvet.",
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
    )
}
