package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import tandera.hackerspace.midnightcafe.R

@Composable
internal fun RecipeImage(
    @DrawableRes image: Int,
    description: String,
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
private fun PreviewRecipeImage() {
    RecipeImage(image = R.drawable.red_velvet, description = "A sliced piece of Red Velvet.")
}
