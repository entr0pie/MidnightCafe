package tandera.hackerspace.midnightcafe.ui.components.rating

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.ui.components.common.Score

@Composable
fun Rating(score: Score, starSize: Dp = 32.dp, betweenSize: Dp = 2.dp) {
    return Row {
        repeat(score.value()) {
            Spacer(modifier = Modifier.width(betweenSize))
            Star(
                modifier = Modifier.size(starSize)
            )
            Spacer(modifier = Modifier.width(betweenSize))
        }
    }
}

@Preview
@Composable
private fun PreviewRating() {
    return Rating(Score.FIVE)
}

