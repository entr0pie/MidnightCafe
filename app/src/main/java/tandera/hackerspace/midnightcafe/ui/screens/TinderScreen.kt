package tandera.hackerspace.midnightcafe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.HateItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.LoveItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.theme.Palette

val CARD_ALIGNMENT: Alignment = BiasAlignment(0f, -0.8f)

@Composable
fun TinderScreen(modifier: Modifier = Modifier) {

    @Composable
    fun renderTinderCard() {
        Box {
            RecipeCard(
                "Red Velvet",
                Score.FIVE,
                R.drawable.red_velvet,
                "A sliced piece of Red Velvet.",
                modifier = Modifier
                    .fillMaxWidth(0.90f)
                    .fillMaxHeight(0.60f),
                titleSize = 32.sp,
                starSize = 24.dp
            )
        }
    }

    @Composable
    fun renderButtons() {
        Row(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HateItButton()
            LoveItButton()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Palette.Lavender),
        contentAlignment = CARD_ALIGNMENT
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            renderTinderCard()
            renderButtons()
        }
    }
}

@Preview
@Composable
private fun TinderScreenPreview() {
    TinderScreen()
}