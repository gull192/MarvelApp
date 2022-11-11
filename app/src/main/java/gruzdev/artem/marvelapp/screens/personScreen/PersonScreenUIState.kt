package gruzdev.artem.marvelapp.screens.personScreen

import gruzdev.artem.marvelapp.core.model.HeroInfo

data class PersonUIState (
    val url: String = "",
    val personName: String = "",
    val description: String = ""
){
    companion object {
        val Empty = PersonUIState()
    }
}

sealed interface PersonScreenUIEvent {
    object OnBackClick : PersonScreenUIEvent
    data class OnGetData (val heroInfo: HeroInfo) : PersonScreenUIEvent
}

sealed interface PersonScreenUIEffect {
    object NavigateBack : PersonScreenUIEvent
}
