package gruzdev.artem.marvelapp.screens.selectPersonScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorScreen(error: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text (
            text = error,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
    }
}
