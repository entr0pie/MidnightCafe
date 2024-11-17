package tandera.hackerspace.midnightcafe.ui.components.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import tandera.hackerspace.midnightcafe.util.ConnectionState
import tandera.hackerspace.midnightcafe.util.connectivityState

private val green = Color(0xff6FCF97)
private val red = Color(0xffEB5757)

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ConnectivityStatusBar(modifier: Modifier = Modifier) {
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available
    val message = if (isConnected) "Connected to the internet" else "No connection available"
    val backgroundColor = if (isConnected) green else red

    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isConnected) {
        isVisible = true
        kotlinx.coroutines.delay(3000)
        isVisible = false
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = modifier
                .background(backgroundColor)
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.size(8.dp))
                Text(message, color = Color.White, fontSize = 15.sp)
            }
        }
    }

}