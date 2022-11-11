package gruzdev.artem.marvelapp.screens.persom_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
import gruzdev.artem.marvelapp.screens.destinations.PersonScreenDestination
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonUIEffect
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonUIState
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonViewModel
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonScreenViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): PersonScreenViewModel
    }
    var repository: MarvelNetworkRepository? = null
        @Inject set

    private val _state = MutableStateFlow(PersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PersonScreenUIEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: PersonScreenUIEvent) {
        when(event) {
            is PersonScreenUIEvent.OnGetData ->{
                viewModelScope.launch {
                    getDataAfterNav(event.characterId)
                }
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
        val hero : Resource<HeroInfo> = repository?.getHeroInfo(id)!!
        when (hero) {
            is Resource.Success ->
                updateView(hero.data!!)
            is Resource.Error ->
                _effect.emit(PersonScreenUIEffect.ErrorToLoadData(hero.message!!))
        }
    }
}


