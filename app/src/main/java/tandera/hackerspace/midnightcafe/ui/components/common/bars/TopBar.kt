package tandera.hackerspace.midnightcafe.ui.components.common.bars

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@OptIn(ExperimentalMaterial3Api::class)
private val COLORS = TopAppBarColors(
    containerColor = Palette.MistyRose,
    scrolledContainerColor = Palette.MistyRose,
    navigationIconContentColor = Palette.Jet,
    titleContentColor = Palette.Jet,
    actionIconContentColor = Palette.Jet,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(title = { content() }, colors = COLORS)
}

@Composable
fun MainTopBar() {
    TopBar() {
        Text(text = "Midnight Cafe")
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar() {
        Text(text = "Midnight Cafe")
    }
}