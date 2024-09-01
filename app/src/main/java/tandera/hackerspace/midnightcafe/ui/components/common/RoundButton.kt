package tandera.hackerspace.midnightcafe.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    size: Dp = 64.dp,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.size(size),
        colors = colors,
        contentPadding = PaddingValues(0.dp),
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewRoundButton() {
    RoundButton {
        Image(
            painter = painterResource(id = R.drawable.cross_icon),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
    }
}