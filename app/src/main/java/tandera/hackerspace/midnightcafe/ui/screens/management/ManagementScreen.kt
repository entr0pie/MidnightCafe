package tandera.hackerspace.midnightcafe.ui.screens.management

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.ui.components.common.bars.TopBarWithArrowBack
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.DeletableRecipeCard
import tandera.hackerspace.midnightcafe.ui.theme.Palette
import tandera.hackerspace.midnightcafe.util.RECIPES

@Composable
fun ManagementScreen(navController: NavController) {
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
                    Text(text = "Manage recipes")
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
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
                    items(RECIPES) { recipe ->
                        DeletableRecipeCard(
                            recipe = recipe,
                            modifier = Modifier
                                .height(128.dp)
                                .padding(0.dp, 2.dp)
                                .clickable { navController.navigate("details") },
                            titleSize = 36.sp
                        )
                    }
                }
            }
        }
    }
}