package tandera.hackerspace.midnightcafe.ui.components.recipe.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.ui.components.common.Score

@Composable
fun Rating(score: Score, starSize: Dp = 12.dp, betweenSize: Dp = 2.dp) {
    Row(horizontalArrangement = Arrangement.spacedBy(betweenSize)) {
        repeat(score.value()) {
            Star(
                modifier = Modifier.size(starSize)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewRating() {
    return Rating(Score.FIVE)
}

