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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import tandera.hackerspace.midnightcafe.entities.RecipeEntity
import tandera.hackerspace.midnightcafe.entities.getMidnightCafeDB
import tandera.hackerspace.midnightcafe.services.http.RECIPES
import tandera.hackerspace.midnightcafe.ui.components.common.bars.BottomBar
import tandera.hackerspace.midnightcafe.ui.components.common.bars.MainTopBar
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.HateItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.buttons.LoveItButton
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RoundedRecipeCard
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.SkeletonRecipeCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

val CARD_ALIGNMENT: Alignment = BiasAlignment(0f, -0.8f)

@Composable
fun TinderScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var recipes by remember { mutableStateOf(listOf<RecipeEntity>()) }
    var index by remember { mutableStateOf(0) }

    val context = LocalContext.current
    val db = remember { getMidnightCafeDB(context) }
    val recipeDao = db.getRecipeDao();

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            RECIPES.map {
                val entity = it.toRecipeEntity()
                recipeDao.save(entity)
            }
            recipes = recipeDao.list()
        }
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
                        if (recipes.getOrNull(index) != null) PolishedCard(
                            navController,
                            recipes.get(index)
                        ) else SkeletonRecipeCard()
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(0.85f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HateItButton(onClick = {
                            index = if (index == recipes.size - 1) 0 else index + 1
                        })
                        LoveItButton(onClick = {
                            index = if (index == recipes.size - 1) 0 else index + 1
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
    recipeEntity: RecipeEntity,
    modifier: Modifier = Modifier
) {
    val recipe = RecipeModel.fromRecipeEntity(recipeEntity)

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