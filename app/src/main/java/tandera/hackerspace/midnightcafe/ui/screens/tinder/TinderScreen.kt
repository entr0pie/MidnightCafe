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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.data.Recipe
import tandera.hackerspace.midnightcafe.data.RecipeFeedViewModel
import tandera.hackerspace.midnightcafe.ui.components.common.bars.BottomBar
import tandera.hackerspace.midnightcafe.ui.components.common.bars.MainTopBar
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.HateItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.LoveItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RoundedRecipeCard
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.SkeletonRecipeCard
import tandera.hackerspace.midnightcafe.ui.theme.Palette

val CARD_ALIGNMENT: Alignment = BiasAlignment(0f, -0.8f)

@Composable
fun TinderScreen(
    navController: NavController,
    viewModel: RecipeFeedViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val recipes by viewModel.recipes.collectAsState()
    var currentRecipe by remember { mutableStateOf<Recipe?>(null) }
    var index by remember { mutableStateOf(0) }


    if (index in recipes.indices) {
        println("Atualizando a receita atual: ${index + 1} / ${recipes.size}")
        currentRecipe = recipes[index]
    } else {
        println("Indice nao esta presente!!! ")
        currentRecipe = null
    }

    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = {
            BottomBar(
                onProfileClick = { navController.navigate("profile") },
                onManagementClick = { navController.navigate("management") }
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
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
                        if (currentRecipe != null) {
                            PolishedCard(navController, currentRecipe!!)
                        } else {
                            SkeletonRecipeCard(text = "Vamos trazer mais receitas em breve!")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(0.85f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HateItButton(onClick = {
                            if (index + 1 < recipes.size) {
                                index += 1 // Move to next recipe
                                return@HateItButton
                            }

                            currentRecipe = null
                        })
                        LoveItButton(onClick = {
                            if (index + 1 < recipes.size) {
                                index += 1 // Move to next recipe
                                return@LoveItButton
                            }

                            currentRecipe = null
                        })
                    }
                }
            }
        }
    }
}


@Composable
private fun PolishedCard(
    navController: NavController,
    recipe: Recipe,
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