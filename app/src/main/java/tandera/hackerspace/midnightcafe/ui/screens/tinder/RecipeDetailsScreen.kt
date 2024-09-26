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
import tandera.hackerspace.midnightcafe.ui.components.common.bars.TopBarWithArrowBack
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.components.recipe.comment.RecipeCommentCard
import tandera.hackerspace.midnightcafe.ui.models.RecipeCommentModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun RecipeDetailsScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopBarWithArrowBack(onReturnClick = {
                navController.popBackStack()
            }) {
                Text(text = "Recipe: Red Velvet")
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
fun IngredientsList() {
    val ingredients = listOf(
        "3 colheres (sopa) de manteiga",
        "2 e 1/2 colheres (sopa) de chocolate em pó",
        "4 ovos",
        "1 xícara e 1/2 colher (sopa) de farinha de trigo",
        "1/2 colher (chá) de vinagre branco",
        "100 ml de leite aquecido",
        "1 e 1/2 colher (sopa) de corante vermelho em gel",
        "1 e 1/4 de xícara de açúcar",
        "1/2 colher (sopa) de fermento em pó",
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
        "Despeje o leite já aquecido na panela, acrescente a manteiga e misture.",
        "Coloque o chocolate em pó e deixe em fogo médio até a manteiga derreter completamente.",
        "Desligue o fogo, coloque o corante, misture e reserve.",
        "Na batedeira, despeje os ovos e o açúcar a bata até dobrar de volume.",
        "Adicione o farinha e o fermento e misture bem.",
        "Depois, acrescente a mistura com a corante aos poucos e mexa mais.",
        "Despeje a massa numa forma redonda e leve ao forno preaquecido a 180 graus por 40 minutos."
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