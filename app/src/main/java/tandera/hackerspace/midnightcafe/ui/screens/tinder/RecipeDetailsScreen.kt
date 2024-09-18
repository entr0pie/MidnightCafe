package tandera.hackerspace.midnightcafe.ui.screens.tinder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard

@Composable
fun RecipeDetailsScreen(navController: NavController) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.40f)
        ) {
            RecipeCard(
                "Red Velvet",
                Score.FIVE,
                R.drawable.red_velvet,
                "A sliced piece of Red Velvet.",
                titleSize = 32.sp,
                starSize = 18.dp
            )
        }

        Text(text = "Testing some functionalities.")
    }


}