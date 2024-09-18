package tandera.hackerspace.midnightcafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import tandera.hackerspace.midnightcafe.ui.components.common.MainTopBar
import tandera.hackerspace.midnightcafe.ui.screens.TinderScreen
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
                    TinderScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}