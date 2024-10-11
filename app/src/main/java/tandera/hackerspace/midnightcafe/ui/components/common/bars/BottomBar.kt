package tandera.hackerspace.midnightcafe.ui.components.common.bars

import androidx.annotation.DrawableRes
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
fun BottomBar(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit = {},
    onManagementClick: () -> Unit = {}
) {
    BottomAppBar {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ManagementButton(onClick = onManagementClick)
            ProfileButton(onClick = onProfileClick)
        }
    }
}

@Composable
fun ManagementButton(modifier: Modifier = Modifier, onClick: () -> Unit) = BarButton(
    image = R.drawable.mangement_icon,
    description = "Manage some recipes",
    modifier = modifier,
    onClick = onClick
)

@Composable
fun ProfileButton(modifier: Modifier = Modifier, onClick: () -> Unit) = BarButton(
    image = R.drawable.heart_icon,
    description = "Enter into the profile",
    modifier = modifier,
    onClick = onClick
)

@Composable
fun BarButton(
    @DrawableRes image: Int,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier.clickable { onClick() }
    )
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}