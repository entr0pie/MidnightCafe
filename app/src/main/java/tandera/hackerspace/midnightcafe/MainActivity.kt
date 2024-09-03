package tandera.hackerspace.midnightcafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.ui.components.common.MainTopBar
import tandera.hackerspace.midnightcafe.ui.components.common.Score
import tandera.hackerspace.midnightcafe.ui.components.recipe.card.RecipeCard
import tandera.hackerspace.midnightcafe.ui.theme.MidnightCafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MidnightCafeTheme {
                Scaffold(
                    topBar = { MainTopBar() }
                ) { innerPadding ->
                    RecipeCard(
                        "Red Velvet",
                        Score.FIVE,
                        R.drawable.red_velvet,
                        "A sliced piece of Red Velvet.",
                        modifier = Modifier
                            .width(150.dp)
                            .height(200.dp)
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MidnightCafeTheme {
        Scaffold(
            topBar = { MainTopBar() }
        ) { innerPadding ->
            Row(modifier = Modifier.fillMaxSize()) {
                RecipeCard(
                    "Red Velvet",
                    Score.FIVE,
                    R.drawable.red_velvet,
                    "A sliced piece of Red Velvet.",
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .fillMaxHeight(0.60f)
                        .align(Alignment.CenterVertically)
                        .padding(innerPadding),
                    titleSize = TextUnit(36.0f, TextUnitType.Sp),
                    starSize = 24.dp
                )
            }
        }
    }
}