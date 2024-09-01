package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun RecipeFade() {
    val gradient = Brush.verticalGradient(
        listOf(
            Color.Transparent,
            Color.Black
        )
    )

    return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    )
}

@Preview
@Composable
private fun PreviewRecipeFade() {
    RecipeFade()
}