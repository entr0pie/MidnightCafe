package tandera.hackerspace.midnightcafe.ui.screens.tinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.HateItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.LoveItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

val CARD_ALIGNMENT: Alignment = BiasAlignment(0f, -0.8f)

val RECIPES = listOf(
    RecipeModel(
        "Red Velvet",
        Score.FIVE,
        R.drawable.red_velvet,
        "A sliced piece of Red Velvet."
    ),
    RecipeModel(
        "Tiramisu",
        Score.THREE,
        R.drawable.tiramisu,
        "A sliced piece of Tiramisu."
    ),
    RecipeModel(
        "Brigadeiro",
        Score.FIVE,
        R.drawable.brigadeiro,
        "A common brigadeiro."
    )
)

@Composable
fun TinderScreen(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }

    fun updateIndex() {
        if (currentIndex == RECIPES.size - 1) {
            currentIndex = 0
            return
        }

        currentIndex++
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
            Box {
                Card(recipe = RECIPES[currentIndex])
            }

            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HateItButton(onClick = { updateIndex() })
                LoveItButton(onClick = { updateIndex() })
            }
        }
    }
}


@Composable
private fun Card(recipe: RecipeModel, modifier: Modifier = Modifier) {
    RecipeCard(
        recipe.title,
        recipe.score,
        recipe.image,
        recipe.imageDescription,
        modifier = modifier
            .fillMaxWidth(0.90f)
            .fillMaxHeight(0.60f),
        titleSize = 32.sp,
        starSize = 24.dp
    )
}