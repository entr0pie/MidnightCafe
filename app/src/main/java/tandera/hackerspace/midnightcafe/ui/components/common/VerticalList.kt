package tandera.hackerspace.midnightcafe.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tandera.hackerspace.midnightcafe.ui.theme.Palette

@Composable
fun VerticalList(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Card(modifier = modifier, colors = Palette.CARD_COLORS) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = title, fontSize = 24.sp)
            HorizontalDivider(thickness = 2.dp)
            content()
        }
    }
}

@Preview
@Composable
fun VerticalListPreview() {
    VerticalList("Vertical List Title") {
        Text(text = "FIRST ELEMENT")
        Text(text = "SECOND ELEMENT")
    }
}