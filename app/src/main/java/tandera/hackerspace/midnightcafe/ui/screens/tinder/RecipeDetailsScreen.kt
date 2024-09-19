package tandera.hackerspace.midnightcafe.ui.screens.tinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.common.VerticalList
import tandera.hackerspace.midnightcafe.ui.components.common.bars.MainTopBar
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.components.recipe.comment.RecipeCommentCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeCommentModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun RecipeDetailsScreen(navController: NavController) {

    Scaffold(
        topBar = { MainTopBar() },
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
                }

                item { CommentCarousel() }
                item { IngredientsList() }
                item { RecipeStepsList() }
            }
        }
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

@Composable
fun IngredientsList() {
    val ingredients = listOf(
        "1 pitada de sal",
        "2kg de farinha",
        "muuuitos morangos"
    )

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
fun RecipeStepsList() {
    val steps = listOf(
        "aqueca o forno em 200 graus celsius",
        "amasse os melhores morangos em um pote",
        "pegue dois ovos e retire a gema"
    )

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