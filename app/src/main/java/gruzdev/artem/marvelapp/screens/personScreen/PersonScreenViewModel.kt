package gruzdev.artem.marvelapp.screens.personScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.dataManager.DataManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonScreenViewModel @Inject constructor(
    private val dataManager: DataManager
) : ViewModel(){

    private val _state = MutableStateFlow(PersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PersonScreenUIEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: PersonScreenUIEvent) {
        when(event) {
            is PersonScreenUIEvent.OnGetData ->
                viewModelScope.launch {
                    getDataAfterNav(event.characterId)
                }

            PersonScreenUIEvent.OnOpenWithArg -> TODO()
        }
    }

    private suspend fun updateView(heroInfo: HeroInfo) {
        _state.update {
            it.copy(
                description = heroInfo.descriptionHero,
                personName = heroInfo.heroName,
                url = heroInfo.photoUrl
            )
        }
    }

    private suspend fun getDataAfterNav(id: Int) {
        val hero : HeroInfo = dataManager?.getHero(id)!!
        updateView(hero)
    }
}


