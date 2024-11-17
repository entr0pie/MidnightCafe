package tandera.hackerspace.midnightcafe.ui.screens.recipe_creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.data.recipe.feed.RecipeFeedViewModel
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.common.bars.TopBarWithArrowBack
import tandera.hackerspace.midnightcafe.ui.theme.Palette
import kotlin.math.roundToInt

@Composable
fun RecipeCreationScreen(
    navController: NavController,
    recipeFeedViewModel: RecipeFeedViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(Score.ONE) }
    var ingredients by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }

    val canCreateRecipe =
        title.isNotBlank() && ingredients.isNotBlank() && instructions.isNotBlank()

    Scaffold(
        topBar = {
            TopBarWithArrowBack(onReturnClick = {
                navController.popBackStack()
            }) {
                Text(text = "Create a new Recipe")
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.Lavender)
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                ScoreSlider(score = score) { score = it }

                TextField(
                    value = ingredients,
                    onValueChange = { ingredients = it },
                    label = { Text(text = "Ingredients") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                TextField(
                    value = instructions,
                    onValueChange = { instructions = it },
                    label = { Text(text = "Instructions") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Button(
                    onClick = {
                        val recipe = buildRecipe(title, score, ingredients, instructions)
                        recipeFeedViewModel.create(recipe)
                        navController.popBackStack()
                    },
                    enabled = canCreateRecipe,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "Create Recipe")
                }
            }
        }
    }
}

@Composable
private fun ScoreSlider(score: Score, onScoreChange: (score: Score) -> Unit) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Score")
        Slider(
            value = score.value().toFloat(),
            onValueChange = { newValue ->
                val receivedScore = Score.of(newValue.roundToInt())
                if (receivedScore != null) {
                    onScoreChange(receivedScore)
                }
            },
            valueRange = 1f..5f,
            steps = 3,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private fun buildRecipe(
    title: String,
    score: Score,
    ingredients: String,
    instructions: String
): Recipe {
    return Recipe(
        title,
        score,
        R.drawable.red_velvet,
        "Uma receita muito boa",
        ingredients,
        instructions
    )
}
