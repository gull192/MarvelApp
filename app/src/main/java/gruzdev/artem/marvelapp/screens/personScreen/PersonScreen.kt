package gruzdev.artem.marvelapp.screens.personScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.FULL_ROUTE_PLACEHOLDER
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gruzdev.artem.marvelapp.screens.personScreen.view.DisplayPersonScreen
import gruzdev.artem.marvelapp.screens.personScreen.view.ErrorScreen
import gruzdev.artem.marvelapp.screens.personScreen.view.LoadingScreen

@Destination(
    route = "person",
    deepLinks = [
        DeepLink(
            uriPattern = "https://myapp.com/$FULL_ROUTE_PLACEHOLDER"
        )
    ]
)
@Composable
fun PersonScreen(navigator: DestinationsNavigator, characterId: Int) {
    val viewModel: PersonScreenViewModel = hiltViewModel()
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
    val uiState by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                PersonScreenUIEffect.NavigateBack -> onBack()
            }
        }
    }

    when (uiState) {
        is PersonUIState.DisplayPerson -> DisplayPersonScreen(
            uiState = uiState as PersonUIState.DisplayPerson,
            onBack = { viewModel.sendEvent(PersonScreenUIEvent.OnBackClick) }
        )
        is PersonUIState.Error -> ErrorScreen(error = (uiState as PersonUIState.Error).error)
        PersonUIState.Loading -> LoadingScreen()
    }
}
