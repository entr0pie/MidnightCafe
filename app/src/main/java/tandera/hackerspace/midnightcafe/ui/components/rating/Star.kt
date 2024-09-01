package tandera.hackerspace.midnightcafe.ui.components.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R

@Composable
fun Star(modifier: Modifier = Modifier) {
    return Image(
        painter = painterResource(id = R.drawable.star),
        contentDescription = "Rating star",
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewStar() {
    return Star(Modifier.width(32.dp))
}