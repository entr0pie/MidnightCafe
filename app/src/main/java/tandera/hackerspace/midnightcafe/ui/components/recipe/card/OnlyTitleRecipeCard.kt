package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.data.recipe.Recipe

@Composable
fun OnlyTitleRecipeCard(
    title: String,
    @DrawableRes image: Int,
    imageDescription: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
) {
    Box(modifier = modifier) {
        RecipeImage(image, imageDescription)
        RecipeFade()
        RecipeFooter(
            title = title,
            titleSize = titleSize,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun OnlyTitleRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
) {
    OnlyTitleRecipeCard(
        title = recipe.title,
        image = recipe.image,
        imageDescription = recipe.imageDescription,
        modifier = modifier,
        titleSize = titleSize
    )
}