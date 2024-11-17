package tandera.hackerspace.midnightcafe.ui.components.recipe.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R
import tandera.hackerspace.midnightcafe.data.recipe.Recipe
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun DeletableRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    onDelete: () -> Unit = {},
) {
    DeletableRecipeCard(
        title = recipe.title,
        image = recipe.image,
        imageDescription = recipe.imageDescription,
        modifier = modifier,
        titleSize = titleSize,
        onDelete = onDelete
    )
}

@Composable
fun DeletableRecipeCard(
    title: String,
    @DrawableRes image: Int,
    imageDescription: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = TextUnit.Unspecified,
    onDelete: () -> Unit = {},
) {
    Box(modifier = modifier) {
        RecipeImage(image, imageDescription)
        RecipeFade()
        RecipeFooter(
            title = title,
            titleSize = titleSize,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )

        TrashButton(onDelete = onDelete)
    }
}

@Composable
private fun BoxScope.TrashButton(modifier: Modifier = Modifier, onDelete: () -> Unit = {}) {
    val dialogState = remember { mutableStateOf(false) }

    when {
        dialogState.value -> {
            AreYouSureDialog(
                onDismiss = { dialogState.value = false },
                onAccept = {
                    dialogState.value = false
                    onDelete()
                })
        }
    }


    Box(
        modifier = modifier
            .background(Palette.Jet)
            .width(50.dp)
            .fillMaxHeight()
            .align(Alignment.CenterEnd)
            .clickable { dialogState.value = true }
    ) {
        Image(
            painter = painterResource(id = R.drawable.trash_icon),
            contentDescription = "",
            modifier = modifier
                .width(50.dp)
                .padding(8.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun AreYouSureDialog(onDismiss: () -> Unit, onAccept: () -> Unit) {
    AlertDialog(
        icon = { Icon(Icons.Default.Info, contentDescription = "An information icon") },
        title = { Text(text = "Are you sure?") },
        text = { Text(text = "This action is irreversible!") },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onAccept() }, colors = Palette.DESTRUCTIVE_BUTTON_COLORS) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

