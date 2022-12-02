package gruzdev.artem.marvelapp

import android.content.BroadcastReceiver
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import gruzdev.artem.marvelapp.ui.theme.MarvelAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var pushBroadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                AppHost()
            }
        }
    }
}
