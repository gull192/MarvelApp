package gruzdev.artem.marvelapp.screens.persom_screen

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gruzdev.artem.marvelapp.screens.persom_screen.components.BackButton
import gruzdev.artem.marvelapp.screens.persom_screen.components.BackgroundImage
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.rememberStateWithLifecycle
import gruzdev.artem.marvelapp.core.ui.di.daggerSavedStateViewModel
import gruzdev.artem.marvelapp.screens.persom_screen.di.personScreenComponent
import gruzdev.artem.marvelapp.ui.theme.Typography

@Destination
@Composable
fun PersonScreen(navigator: DestinationsNavigator, characterId: Int) {
    val activity = LocalContext.current as Activity
    val viewModel =  daggerSavedStateViewModel {
        personScreenComponent.getInstance(activity).personScreenViewModelFactory.create(it)
    }
    viewModel.sendEvent(PersonScreenUIEvent.OnGetData(characterId))
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