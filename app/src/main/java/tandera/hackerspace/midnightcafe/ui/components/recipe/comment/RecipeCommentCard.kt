package tandera.hackerspace.midnightcafe.ui.components.recipe.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tandera.hackerspace.midnightcafe.ui.models.RecipeCommentModel
import tandera.hackerspace.midnightcafe.ui.theme.Palette

private val CARD_COLORS = CardColors(
    Palette.Lavender,
    Palette.Jet,
    Palette.Timberwolf,
    Palette.Jet
)

@Composable
fun RecipeCommentCard(
    recipeComment: RecipeCommentModel,
    modifier: Modifier = Modifier,
    commentSize: TextUnit = 18.sp,
    authorSize: TextUnit = 12.sp
) {
    Card(modifier = modifier, colors = CARD_COLORS) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\"${recipeComment.comment}\"",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = commentSize
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "~ ${recipeComment.author}",
                    fontSize = authorSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecipeCommentCardPreview() {
    RecipeCommentCard(
        RecipeCommentModel("An absolute masterpiece!", "Jaquan"),
        modifier = Modifier
            .width(200.dp)
            .height(150.dp)
    )
}