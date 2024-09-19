package tandera.hackerspace.midnightcafe.ui.components.common.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tandera.hackerspace.midnightcafe.R

@Composable
fun BottomBar(modifier: Modifier = Modifier, onProfileClick: () -> Unit = {}) {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            ProfileButton(onClick = onProfileClick)
        }
    }
}

@Composable
fun ProfileButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.heart_icon),
        contentDescription = "Enter into the profile",
        modifier = Modifier.clickable { onClick() }
    )
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}