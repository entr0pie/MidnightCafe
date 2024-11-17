package tandera.hackerspace.midnightcafe.ui.screens.tinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.data.recipe.SelectedRecipeViewModel
import tandera.hackerspace.midnightcafe.extensions.toRawList
import tandera.hackerspace.midnightcafe.ui.components.common.VerticalList
import tandera.hackerspace.midnightcafe.ui.components.common.bars.TopBarWithArrowBack
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.components.recipe.comment.RecipeCommentCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeCommentModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun RecipeDetailsScreen(
    navController: NavController,
    selectedRecipeViewModel: SelectedRecipeViewModel,
) {
    val recipe = selectedRecipeViewModel.selectedRecipe.value
    if (recipe == null) {
        navController.popBackStack()
        return
    }

    Scaffold(
        topBar = {
            TopBarWithArrowBack(onReturnClick = {
                navController.popBackStack()
            }) {
                Text(text = "Recipe: ${recipe.title}")
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.Lavender)
                .padding(innerPadding),
        ) {
            LazyColumn {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {
                        RecipeCard(
                            recipe,
                            titleSize = 32.sp,
                            starSize = 18.dp
                        )
                    }
                }

                item { CommentCarousel() }
                item { IngredientsList(recipe) }
                item { RecipeStepsList(recipe) }
            }
        }
    }
}

@Composable
fun CommentCarousel() {
    val comments = listOf(
        RecipeCommentModel("Um dos melhores bolos que já experimentei!", "Leonardo Da Vinci"),
        RecipeCommentModel("Um excelente bolo.", "Presidente da República"),
        RecipeCommentModel("Uma receita ótima para hipertrofia!", "Renato Cariani")
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(comments.size) { index ->
            RecipeCommentCard(
                recipeComment = comments[index],
                modifier = Modifier.fillMaxHeight(0.25f)
            )
        }
    }
}

@Composable
fun IngredientsList(recipe: Recipe) {

    val ingredients = recipe.ingredients.toRawList()

    VerticalList(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), title = "Ingredientes"
    ) {
        ingredients.forEach {
            Text(text = it)
        }
    }
}

@Composable
fun RecipeStepsList(recipe: Recipe) {
    val steps = recipe.instructions.toRawList()

    VerticalList(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), title = "Modo de preparo"
    ) {
        steps.forEach {
            Text(text = it)
        }
    }
}