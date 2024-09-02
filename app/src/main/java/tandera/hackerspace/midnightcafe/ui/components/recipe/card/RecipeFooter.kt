package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.recipe.rating.Rating

@Composable
internal fun RecipeFooter(title: String, score: Score, modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = title, color = Color.White)
            Rating(score = score)
        }
    }
}

@Composable
internal fun RecipeFooter(title: String, modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = title, color = Color.White)
        }
    }
}

@Preview
@Composable
private fun NameAndScoreRecipeFooter() {
    RecipeFooter("Red Velvet", Score.FIVE)
}

@Preview
@Composable
private fun OnlyNameRecipeFooter() {
    RecipeFooter("Red Velvet")
}