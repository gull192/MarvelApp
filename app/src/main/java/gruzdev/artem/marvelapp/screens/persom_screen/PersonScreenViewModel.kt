package gruzdev.artem.marvelapp.screens.persom_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.destinations.PersonScreenDestination
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonUIEffect
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonScreenViewModel : ViewModel(){

    private val _state = MutableStateFlow(PersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PersonScreenUIEvent>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: PersonScreenUIEvent) {
        when(event) {
            is PersonScreenUIEvent.OnGetData ->{
                CoroutineScope(Dispatchers.Default).launch {
                    loadData(event.heroInfo)
                }
            }
        }
    }

    suspend fun loadData(heroInfo: HeroInfo) {
        _state.update {
            it.copy(
                description = heroInfo.descriptionHero,
                personName = heroInfo.heroName,
                url = heroInfo.photoUrl
            )
        }
    }
}


