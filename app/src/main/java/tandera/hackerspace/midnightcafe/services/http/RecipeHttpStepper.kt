package tandera.hackerspace.midnightcafe.services.http

import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.extensions.toRawString
import tandera.hackerspace.midnightcafe.services.LoopStepper
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.models.RecipeModel

private val DEFAULT_INGREDIENTS = listOf(
    "3 colheres (sopa) de manteiga",
    "2 e 1/2 colheres (sopa) de chocolate em pó",
    "4 ovos",
    "1 xícara e 1/2 colher (sopa) de farinha de trigo",
    "1/2 colher (chá) de vinagre branco",
    "100 ml de leite aquecido",
    "1 e 1/2 colher (sopa) de corante vermelho em gel",
    "1 e 1/4 de xícara de açúcar",
    "1/2 colher (sopa) de fermento em pó",
).toRawString()

private val DEFAULT_INSTRUCTIONS = listOf(
    "Despeje o leite já aquecido na panela, acrescente a manteiga e misture.",
    "Coloque o chocolate em pó e deixe em fogo médio até a manteiga derreter completamente.",
    "Desligue o fogo, coloque o corante, misture e reserve.",
    "Na batedeira, despeje os ovos e o açúcar a bata até dobrar de volume.",
    "Adicione o farinha e o fermento e misture bem.",
    "Depois, acrescente a mistura com a corante aos poucos e mexa mais.",
    "Despeje a massa numa forma redonda e leve ao forno preaquecido a 180 graus por 40 minutos."
).toRawString()

val RECIPES = listOf(
    RecipeModel(
        "Red Velvet",
        Score.FIVE,
        R.drawable.red_velvet,
        "A sliced piece of Red Velvet.",
        DEFAULT_INGREDIENTS,
        DEFAULT_INSTRUCTIONS,
    ),

    RecipeModel(
        "Tiramisu",
        Score.THREE,
        R.drawable.tiramisu,
        "A sliced piece of Tiramisu.",
        DEFAULT_INGREDIENTS,
        DEFAULT_INSTRUCTIONS
    ),

    RecipeModel(
        "Brigadeiro",
        Score.FIVE,
        R.drawable.brigadeiro,
        "A common brigadeiro.",
        DEFAULT_INGREDIENTS,
        DEFAULT_INSTRUCTIONS
    )
)

class RecipeHttpStepper() : LoopStepper<RecipeModel>(*RECIPES.toTypedArray()) {}