package tandera.hackerspace.midnightcafe.ui.screens.tinder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.components.recipe.comment.RecipeCommentCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeCommentModel

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

        CommentCarousel()
    }
}

@Composable
fun CommentCarousel() {
    val comments = listOf(
        RecipeCommentModel("A work of art!", "Da Vinci"),
        RecipeCommentModel("Made my mind blow. Literally.", "JFK"),
        RecipeCommentModel("Sliced", "Renato Cariani")
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
