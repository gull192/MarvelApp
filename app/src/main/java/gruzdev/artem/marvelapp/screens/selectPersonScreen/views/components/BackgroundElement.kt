package gruzdev.artem.marvelapp.screens.selectPersonScreen.views.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@SuppressLint("InternalInsetResource", "DiscouragedApi")
@Composable
fun BackgroundElement(color: Color, modifier: Modifier = Modifier) {
    // LocalConfiguration.current.screenHeightDp gives bad height,
    // and needs to get status bar height
    // get statusBarHeight in pixels
    val context = LocalContext.current
    var statusBarHeight = 0
    val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
    }

    // convert pixels to dp
    val screenPixelDensity = context.resources.displayMetrics.density
    val statusBarHeightDp = statusBarHeight / screenPixelDensity

    val height = LocalConfiguration.current.screenHeightDp + statusBarHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val proportion = 0.5f

    Canvas(
        modifier = modifier,
    ) {
        ///////////////////
        //            A  //
        //            |  // this triangle
        //        B - c  //
        ///////////////////

        val path = Path()
        path.moveTo(width.dp.toPx(), height.dp.toPx() * proportion) // A
        path.lineTo(0f, height.dp.toPx())                    // B
        path.lineTo(width.dp.toPx(), height.dp.toPx())          // C
        path.lineTo(width.dp.toPx(), height.dp.toPx() * proportion) // A
        path.close()
        drawPath(path = path, color = color)
    }
}
