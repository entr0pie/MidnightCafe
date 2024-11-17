package tandera.hackerspace.midnightcafe.ui.components.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import tandera.hackerspace.midnightcafe.util.ConnectionState
import tandera.hackerspace.midnightcafe.util.connectivityState

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ConnectivityStatusBar(modifier: Modifier = Modifier) {
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available
    val text = if (isConnected) "Connected to the internet" else "No connection available"

    val toast = Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT)
    toast.show()
}