package gruzdev.artem.marvelapp.screens.selectPersonScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.dataManager.DataManager
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectPersonViewModel @Inject constructor(
    private val dataManager: DataManager
) : ViewModel() {

    private val _state = MutableStateFlow<SelectPersonUIState>(SelectPersonUIState.Loading)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SelectPersonUIEffect>()
    val effect = _effect.asSharedFlow()

    private var needToLoadData = true

    fun sendEvent(event: SelectPersonUIEvent) {
        when (event) {
            is SelectPersonUIEvent.OnCurrentIndexChange ->
                viewModelScope.launch {
                    changeCurrentItem(event.newIndex)
                }

            is SelectPersonUIEvent.OnclickHero ->
                viewModelScope.launch {
                    navigateToHeroDetails(event.heroCard)
                }
            SelectPersonUIEvent.OnOpenScreen ->
                viewModelScope.launch {
                    if(needToLoadData) {
                        loadData()
                        needToLoadData = false
                    }
                }
        }
    }

    private fun changeCurrentItem(newIndex: Int) {
        val oldState: SelectPersonUIState.DisplayHeroes =
            _state.value as SelectPersonUIState.DisplayHeroes
        _state.update {
            oldState.copy(
                currentIndex = newIndex
            )
        }
        _state.update {
            oldState.copy(backgroundColor = oldState.listHero[newIndex].color)
        }
    }

    private suspend fun navigateToHeroDetails(heroCard: HeroCard) {
        _effect.emit(SelectPersonUIEffect.NavigateToPersonScreen(heroCard.id))
    }

    private suspend fun loadData() {
        _state.update {
            SelectPersonUIState.Loading
        }

        when (val hero: Resource<List<HeroCard>> = dataManager.getAll()) {
            is Resource.Success ->
                updateView(hero.data!!)
            is Resource.Error ->
                setError(hero.message!!)
        }
    }

    private fun updateView(heroCards: List<HeroCard>) {

        val oldState = if (_state.value is SelectPersonUIState.DisplayHeroes)
            _state.value as SelectPersonUIState.DisplayHeroes
        else SelectPersonUIState.DisplayHeroes()

        _state.update {
            oldState.copy(
                listHero = heroCards,
                backgroundColor = heroCards[oldState.currentIndex].color,
                getDataIsSuccessful = true
            )
        }
    }

    private fun setError(error: String) {
        _state.update {
            SelectPersonUIState.Error(error)
        }
    }
}
