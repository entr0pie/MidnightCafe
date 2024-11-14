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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tandera.hackerspace.midnightcafe.ui.screens.management.ManagementScreen
import tandera.hackerspace.midnightcafe.ui.screens.profile.ProfileScreen
import tandera.hackerspace.midnightcafe.ui.screens.tinder.RecipeDetailsScreen
import tandera.hackerspace.midnightcafe.ui.screens.tinder.TinderScreen
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
    NavHost(navController = navController, startDestination = "tinder") {
        composable("tinder") { TinderScreen(navController) }
        composable("details") { RecipeDetailsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("management") { ManagementScreen(navController) }
    }
}