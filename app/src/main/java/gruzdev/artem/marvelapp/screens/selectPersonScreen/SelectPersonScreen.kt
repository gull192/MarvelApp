package gruzdev.artem.marvelapp.screens.selectPersonScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gruzdev.artem.marvelapp.core.rememberStateWithLifecycle
import gruzdev.artem.marvelapp.core.showToast
import gruzdev.artem.marvelapp.screens.destinations.PersonScreenDestination
import gruzdev.artem.marvelapp.screens.selectPersonScreen.views.DisplayHeroesScreen
import gruzdev.artem.marvelapp.screens.selectPersonScreen.views.ErrorScreen
import gruzdev.artem.marvelapp.screens.selectPersonScreen.views.LoadingScreen

@RootNavGraph(start = true)
@Destination
@Composable
fun SelectPersonScreen(navigator: DestinationsNavigator) {
    val viewModel: SelectPersonViewModel = hiltViewModel()
    viewModel.sendEvent(SelectPersonUIEvent.OnOpenScreen)
    SelectPersonScreen(
        viewModel = viewModel,
        onNavigateToPersonScreen = {
            navigator.navigate(
                PersonScreenDestination(it)
            )
        }
    )
}

@Composable
private fun SelectPersonScreen(
    viewModel: SelectPersonViewModel,
    onNavigateToPersonScreen: (Int) -> Unit
) {
    val uiState by rememberStateWithLifecycle(viewModel.state)
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SelectPersonUIEffect.NavigateToPersonScreen ->
                    onNavigateToPersonScreen(effect.characterId)

                is SelectPersonUIEffect.ErrorToLoadData ->
                    showToast(context, effect.error)
            }
        }
    }
    when (uiState) {
        is SelectPersonUIState.DisplayHeroes -> DisplayHeroesScreen(
            uiState = uiState as SelectPersonUIState.DisplayHeroes,
            onCurrentIndexChange = { viewModel.sendEvent(SelectPersonUIEvent.OnCurrentIndexChange(it)) },
            onClickHero = { viewModel.sendEvent(SelectPersonUIEvent.OnclickHero(it)) }
        )
        is SelectPersonUIState.Loading -> LoadingScreen()
        is SelectPersonUIState.Error -> {
            val err = uiState as SelectPersonUIState.Error
            ErrorScreen(err.error)
        }
    }
}


