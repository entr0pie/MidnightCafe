package tandera.hackerspace.midnightcafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tandera.hackerspace.midnightcafe.data.recipe.SelectedRecipeViewModel
import tandera.hackerspace.midnightcafe.ui.screens.ManagementScreen
import tandera.hackerspace.midnightcafe.ui.screens.ProfileScreen
import tandera.hackerspace.midnightcafe.ui.screens.RecipeCreationScreen
import tandera.hackerspace.midnightcafe.ui.screens.RecipeDetailsScreen
import tandera.hackerspace.midnightcafe.ui.screens.TinderScreen
import tandera.hackerspace.midnightcafe.ui.theme.MidnightCafeTheme
import tandera.hackerspace.midnightcafe.ui.theme.Palette

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MidnightCafeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Palette.Lavender)
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    val selectedRecipeViewModel: SelectedRecipeViewModel = viewModel()

    NavHost(navController = navController, startDestination = "tinder") {
        composable("tinder") { TinderScreen(navController, selectedRecipeViewModel) }
        composable("details") { RecipeDetailsScreen(navController, selectedRecipeViewModel) }
        composable("profile") { ProfileScreen(navController, selectedRecipeViewModel) }
        composable("management") { ManagementScreen(navController, selectedRecipeViewModel) }
        composable("create_recipe") { RecipeCreationScreen(navController) }
    }
}