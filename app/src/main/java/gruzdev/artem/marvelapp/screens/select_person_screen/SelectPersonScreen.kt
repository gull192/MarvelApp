package gruzdev.artem.marvelapp.screens.select_person_screen

import android.util.Log
import gruzdev.artem.marvelapp.core.navigation.navigate
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.core.navigation.Screens
import gruzdev.artem.marvelapp.core.rememberStateWithLifecycle
import gruzdev.artem.marvelapp.screens.select_person_screen.components.BackgroundElement
import gruzdev.artem.marvelapp.screens.select_person_screen.components.RowHero
import gruzdev.artem.marvelapp.ui.theme.Dune
import gruzdev.artem.marvelapp.ui.theme.Typography

@Composable
fun SelectPersonScreen(navController: NavController) {
    SelectPersonScreen(
        navController = navController,
        viewModel = SelectPersonViewModel()
    )
}

@Composable
private fun SelectPersonScreen(
    navController: NavController,
    viewModel: SelectPersonViewModel,
) {

    val uiState by rememberStateWithLifecycle(viewModel.state)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SelectPersonUIEffect.NavigateToPersonScreen -> {
                    Log.e("NAVIGATION1", effect.heroInfo.photoUrl)
                    navController.navigate(
                        route = Screens.PersonDetailsScreen.name,
                        params = bundleOf("HERO_INFO" to effect.heroInfo)
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dune)
    ) {

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
