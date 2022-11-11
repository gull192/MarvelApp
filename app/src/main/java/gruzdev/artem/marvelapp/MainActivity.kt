package gruzdev.artem.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import gruzdev.artem.marvelapp.main.di.mainComponent
import gruzdev.artem.marvelapp.ui.theme.MarvelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent.getInstance(this).inject(this)
        setContent {
            MarvelAppTheme {
                AppHost()
            }
        }
    }
}
