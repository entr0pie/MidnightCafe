package tandera.hackerspace.midnightcafe.ui.components.recipe.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.RoundButton
import tandera.hackerspace.midnightcafe.ui.theme.Palette

private val COLORS = ButtonColors(
    Palette.Jet,
    Palette.MistyRose,
    Palette.Bittersweet,
    Palette.Bittersweet,
)

@Composable
fun LoveItButton(
    onClick: () -> Unit = {},
) = RoundButtonWithImage(
    image = R.drawable.heart_icon,
    imageDescription = "Love it!",
    onClick = onClick
)

@Composable
fun HateItButton(
    onClick: () -> Unit = {},
) = RoundButtonWithImage(
    image = R.drawable.cross_icon,
    imageDescription = "Hate it!",
    onClick = onClick
)

@Composable
private fun RoundButtonWithImage(
    @DrawableRes image: Int,
    imageDescription: String,
    imageSize: Dp = 84.dp,
    onClick: () -> Unit,
    size: Dp = 128.dp,
) {
    RoundButton(onClick = onClick, size = size, colors = COLORS) {
        Image(
            painter = painterResource(id = image),
            contentDescription = imageDescription,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Preview
@Composable
private fun PreviewLoveItButton() {
    LoveItButton()
}

@Preview
@Composable
private fun PreviewHateItButton() {
    HateItButton()
}