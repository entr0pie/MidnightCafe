package tandera.hackerspace.midnightcafe.ui.screens.tinder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.services.Stepper
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.HateItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.LoveItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RoundedRecipeCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

val CARD_ALIGNMENT: Alignment = BiasAlignment(0f, -0.8f)

@Composable
fun TinderScreen(
    navController: NavController,
    stepper: Stepper<RecipeModel>,
    modifier: Modifier = Modifier
) {
    var currentRecipe by remember { mutableStateOf(stepper.current()) }

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
                PolishedCard(navController, recipe = currentRecipe)
            }

            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HateItButton(onClick = { currentRecipe = stepper.step() })
                LoveItButton(onClick = { currentRecipe = stepper.step() })
            }
        }
    }
}


@Composable
private fun PolishedCard(
    navController: NavController,
    recipe: RecipeModel,
    modifier: Modifier = Modifier
) {
    RoundedRecipeCard(
        recipe.title,
        recipe.score,
        recipe.image,
        recipe.imageDescription,
        modifier = modifier
            .fillMaxWidth(0.90f)
            .fillMaxHeight(0.60f)
            .clickable {
                navController.navigate("details")
            },
        titleSize = 32.sp,
        starSize = 24.dp
    )
}