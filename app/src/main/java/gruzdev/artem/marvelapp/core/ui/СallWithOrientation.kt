package gruzdev.artem.marvelapp.core.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import javax.annotation.meta.When

@Composable
fun CallWithOrientation(
    LandScape: @Composable () -> Unit,
    Portrait: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> LandScape()
        else -> Portrait()
    }
}