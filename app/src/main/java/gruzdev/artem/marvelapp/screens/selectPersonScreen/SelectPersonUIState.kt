package gruzdev.artem.marvelapp.screens.selectPersonScreen

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import gruzdev.artem.marvelapp.ui.theme.Dune

@Immutable
sealed class SelectPersonUIState {
    data class DisplayHeroes(
        val backgroundColor: Color = Dune,
        val listHero: List<HeroCard> = listOf(),
        val currentIndex: Int = 0,
        val currentOffset: Int = 0,
        val isLoading: Boolean = false,
        val endReached: Boolean = false,
        val errorToLoadData: String = ""
    ) : SelectPersonUIState() {
        companion object {
            val Empty = SelectPersonUIState.DisplayHeroes()
        }
    }
    data class Error(val error: String) : SelectPersonUIState()
    object Loading : SelectPersonUIState()
}


@Immutable
sealed interface SelectPersonUIEvent {
    data class OnCurrentIndexChange(val newIndex: Int) : SelectPersonUIEvent
    data class OnclickHero(val heroCard: HeroCard) : SelectPersonUIEvent
    object OnOpenScreen : SelectPersonUIEvent
    object OnLoadPagingData : SelectPersonUIEvent
}

@Immutable
sealed interface SelectPersonUIEffect {
    data class NavigateToPersonScreen(val characterId: Int) : SelectPersonUIEffect
    data class ErrorToLoadData(val error: String) : SelectPersonUIEffect
}

