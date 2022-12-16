package gruzdev.artem.marvelapp.screens.personScreen.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gruzdev.artem.marvelapp.screens.personScreen.PersonUIState
import gruzdev.artem.marvelapp.screens.personScreen.view.components.BackButton
import gruzdev.artem.marvelapp.screens.personScreen.view.components.BackgroundImage
import gruzdev.artem.marvelapp.ui.theme.Typography

@Composable
fun DisplayPersonScreen(
    uiState: PersonUIState.DisplayPerson,
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage(url = uiState.url)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            BackButton(
                onClick = { onBack() },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 16.dp)

            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = uiState.personName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSecondary,
                    maxLines = 4
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = uiState.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSecondary,
                    maxLines = 6
                )
            }
        }
    }
}
