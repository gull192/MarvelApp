package gruzdev.artem.marvelapp.screens.persom_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gruzdev.artem.marvelapp.screens.persom_screen.components.BackButton
import gruzdev.artem.marvelapp.screens.persom_screen.components.BackgroundImage
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.rememberStateWithLifecycle
import gruzdev.artem.marvelapp.ui.theme.Typography

@Destination
@Composable
fun PersonScreen(heroInfo: HeroInfo, navigator: DestinationsNavigator) {
    val viewModel = PersonScreenViewModel()
    viewModel.sendEvent(PersonScreenUIEvent.OnGetData(heroInfo))
    PersonScreen(
        viewModel = viewModel,
        onBack = { navigator.popBackStack() }
    )
}

@Composable
private fun PersonScreen(
    viewModel: PersonScreenViewModel,
    onBack: () -> Unit
) {
    val uiState by rememberStateWithLifecycle(viewModel.state)
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage(url = uiState.url)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            BackButton(
                onClick = { onBack() },
                modifier = Modifier.padding(top = 16.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = uiState.personName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = Typography.h3,
                    color = Color.White,
                    )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = uiState.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = Typography.h4,
                    color = Color.White,
                )
            }
        }
    }
}