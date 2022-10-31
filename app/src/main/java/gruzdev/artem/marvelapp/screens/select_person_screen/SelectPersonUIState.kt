package gruzdev.artem.marvelapp.screens.select_person_screen

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import gruzdev.artem.marvelapp.ui.theme.Dune

@Immutable
data class SelectPersonUIState(
    val backgroundColor: Color = Dune,
    val listHero: List<HeroCard> = listOf(),
    val currentIndex: Int = 0,
) {
    companion object {
        val Empty = SelectPersonUIState()
    }
}

@Immutable
sealed class SelectPersonUIEvent {
    class OnCurrentIndexChange(val newIndex: Int): SelectPersonUIEvent()
    class OnclickHero(val heroCard: HeroCard): SelectPersonUIEvent()
}

@Immutable
sealed class SelectPersonUIEffect {
    class NavigateToPersonScreen(val heroInfo: HeroInfo) : SelectPersonUIEffect()
}

