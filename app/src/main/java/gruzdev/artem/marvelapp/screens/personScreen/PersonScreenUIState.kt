package gruzdev.artem.marvelapp.screens.personScreen

import javax.annotation.concurrent.Immutable

@Immutable
sealed class PersonUIState {
    data class DisplayPerson(
        val url: String = "",
        val personName: String = "",
        val description: String = ""
    ) : PersonUIState() {
        companion object {
            val Empty = DisplayPerson()
        }
    }
    object Loading : PersonUIState()
    data class Error (val error: String) : PersonUIState()
}
@Immutable
sealed interface PersonScreenUIEvent {
    object OnBackClick : PersonScreenUIEvent
    data class OnGetData (val characterId: Int) : PersonScreenUIEvent
    object OnOpenWithArg : PersonScreenUIEvent
}

@Immutable
sealed interface PersonScreenUIEffect {
    object NavigateBack : PersonScreenUIEffect
}
