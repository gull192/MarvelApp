package gruzdev.artem.marvelapp.screens.persom_screen

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
    class OnGetData (val characterId: Int) : PersonScreenUIEvent
    object OnOpenWithArg : PersonScreenUIEvent
}

sealed interface PersonScreenUIEffect {
    object NavigateBack : PersonScreenUIEffect
    class ErrorToLoadData(val error: String) : PersonScreenUIEffect
}
