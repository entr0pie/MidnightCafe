package tandera.hackerspace.midnightcafe.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.services.http.RECIPES
import tandera.hackerspace.midnightcafe.ui.components.common.bars.BottomBar
import tandera.hackerspace.midnightcafe.ui.components.common.bars.MainTopBar
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.OnlyTitleRecipeCard
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun ProfileScreen(navController: NavController) {
    Box(modifier = Modifier.background(Palette.Lavender))
    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = {
            BottomBar() {
                navController.navigate("profile")
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(RECIPES) { recipe ->
                    OnlyTitleRecipeCard(
                        recipeModel = recipe,
                        modifier = Modifier
                            .height(128.dp)
                            .clickable { navController.navigate("details") },
                        titleSize = 36.sp
                    )
                }
            }
        }
    }
}