package gruzdev.artem.marvelapp.screens.select_person_screen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.rememberStateWithLifecycle
import gruzdev.artem.marvelapp.core.ui.di.daggerSavedStateViewModel
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
import gruzdev.artem.marvelapp.screens.destinations.PersonScreenDestination
import gruzdev.artem.marvelapp.screens.select_person_screen.components.BackgroundElement
import gruzdev.artem.marvelapp.screens.select_person_screen.components.RowHero
import gruzdev.artem.marvelapp.screens.select_person_screen.di.selectPersonComponent
import gruzdev.artem.marvelapp.ui.theme.Dune
import gruzdev.artem.marvelapp.ui.theme.Typography
import javax.inject.Inject

@RootNavGraph(start = true)
@Destination
@Composable
fun SelectPersonScreen(navigator: DestinationsNavigator) {
    val activity = LocalContext.current as Activity
    val viewModel =  daggerSavedStateViewModel {
        selectPersonComponent.getInstance(activity).selectPersonViewModelFactory.create(it)
    }
    viewModel.sendEvent(SelectPersonUIEvent.OnOpenScreen)
    SelectPersonScreen(
        viewModel = viewModel,
        navigateToPersonScreen = {
            navigator.navigate(PersonScreenDestination(it))
        }
    )
}

@Composable
private fun SelectPersonScreen(
    viewModel: SelectPersonViewModel,
    navigateToPersonScreen: (Int) -> Unit
) {
    val uiState by rememberStateWithLifecycle(viewModel.state)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SelectPersonUIEffect.NavigateToPersonScreen -> {
                    navigateToPersonScreen(effect.characterId)
                }
                else -> {}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dune)
    ) {
        Log.e("COLORCUR", uiState.backgroundColor.value.toString())
        BackgroundElement(color = uiState.backgroundColor)

        Column {
            Image(
                painter = painterResource(R.drawable.marvel),
                contentDescription = null,
                modifier = Modifier
                    .scale(0.4f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = stringResource(id = R.string.main_text),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.h4,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))
            RowHero(heros = uiState.listHero,
                changeCurrentItem = {
                    viewModel.sendEvent(
                        SelectPersonUIEvent.OnCurrentIndexChange(it)
                    )
                },
                onclick = {
                    viewModel.sendEvent(SelectPersonUIEvent.OnclickHero(it))
                }
            )

        }
    }
}