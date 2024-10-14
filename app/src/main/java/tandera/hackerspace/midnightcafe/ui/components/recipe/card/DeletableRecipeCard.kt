package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun DeletableRecipeCard(
    recipeModel: RecipeModel,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    onDelete: () -> Unit = {},
) {
    DeletableRecipeCard(
        title = recipeModel.title,
        image = recipeModel.image,
        imageDescription = recipeModel.imageDescription,
        modifier = modifier,
        titleSize = titleSize,
        onDelete = onDelete
    )
}

@Composable
fun DeletableRecipeCard(
    title: String,
    @DrawableRes image: Int,
    imageDescription: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    onDelete: () -> Unit = {},
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

        Box(
            modifier = modifier
                .background(Palette.Jet)
                .width(50.dp)
                .height(50.dp)
                .align(Alignment.CenterEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.trash_icon),
                contentDescription = "",
                modifier = modifier
                    .width(50.dp)
                    .padding(8.dp)
                    .clickable { onDelete() }
            )
        }
    }
}