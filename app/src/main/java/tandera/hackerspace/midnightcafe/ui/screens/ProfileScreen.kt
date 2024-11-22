package tandera.hackerspace.midnightcafe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.data.recipe.SelectedRecipeViewModel
import tandera.hackerspace.midnightcafe.data.recipe.liked.LikedRecipesViewModel
import tandera.hackerspace.midnightcafe.ui.components.common.ConnectivityStatusBar
import tandera.hackerspace.midnightcafe.ui.components.common.bars.TopBarWithArrowBack
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.OnlyTitleRecipeCard
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun ProfileScreen(
    navController: NavController,
    selectedRecipeViewModel: SelectedRecipeViewModel,
    viewModel: LikedRecipesViewModel = viewModel()
) {
    val recipes by viewModel.likedRecipes.collectAsState()

    Box(
        modifier = Modifier
            .background(Palette.Lavender)
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopBarWithArrowBack(onReturnClick = {
                    navController.popBackStack()
                }) {
                    Text(text = "Liked Recipes")
                }
            },
            bottomBar = {
                Column(modifier = Modifier.padding(bottom = 32.dp)) {
                    ConnectivityStatusBar()
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .background(Palette.Lavender)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(Palette.Lavender)
                ) {
                    items(recipes) { recipe ->
                        OnlyTitleRecipeCard(
                            recipe = recipe,
                            modifier = Modifier
                                .height(128.dp)
                                .padding(0.dp, 2.dp)
                                .clickable {
                                    selectedRecipeViewModel.selectRecipe(recipe)
                                    navController.navigate("details")
                                },
                            titleSize = 36.sp
                        )
                    }
                }
            }
        }
    }
}