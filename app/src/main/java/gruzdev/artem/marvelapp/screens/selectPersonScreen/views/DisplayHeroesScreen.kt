package gruzdev.artem.marvelapp.screens.selectPersonScreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.screens.selectPersonScreen.SelectPersonUIState
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import gruzdev.artem.marvelapp.screens.selectPersonScreen.views.components.BackgroundElement
import gruzdev.artem.marvelapp.screens.selectPersonScreen.views.components.RowHero
import gruzdev.artem.marvelapp.ui.theme.Dune
import gruzdev.artem.marvelapp.ui.theme.Typography

@Composable
fun DisplayHeroesScreen(
    uiState: SelectPersonUIState.DisplayHeroes,
    onCurrentIndexChange: (Int) -> Unit,
    onClickHero: (HeroCard) -> Unit,
    onPagingHeroes: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dune)
    ) {
        BackgroundElement(color = uiState.backgroundColor)
        Column (modifier = Modifier.fillMaxSize()) {
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
            RowHero(
                heroes = uiState.listHero,
                isLoading = uiState.isLoading,
                endReached = uiState.endReached,
                onPagingHeroes = { onPagingHeroes() },
                onValueChange = {
                    onCurrentIndexChange(it)
                },
                onclick = {
                    onClickHero(it)
                },
            )
        }
    }
}
