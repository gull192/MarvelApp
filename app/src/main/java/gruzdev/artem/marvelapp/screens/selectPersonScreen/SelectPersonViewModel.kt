package gruzdev.artem.marvelapp.screens.selectPersonScreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.dataManager.DataManager
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
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

    private val _state = MutableStateFlow(SelectPersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SelectPersonUIEffect>()
    val effect = _effect.asSharedFlow()

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
                viewModelScope.launch { loadData() }
        }
    }

    private fun changeCurrentItem(newIndex: Int) {
        Log.e("TAF", newIndex.toString())
        _state.update {
            it.copy(currentIndex = newIndex)
        }
        _state.update {
            it.copy(backgroundColor = state.value.listHero[newIndex].color)
        }
        Log.e("COLOR", _state.value.backgroundColor.toString())
    }

    private suspend fun navigateToHeroDetails(heroCard: HeroCard) {
        _effect.emit(SelectPersonUIEffect.NavigateToPersonScreen(heroCard.id))
    }

    private suspend fun loadData() {
        when (val hero : Resource<List<HeroCard>> = dataManager.getAll()) {
            is Resource.Success ->
                updateView(hero.data!!)
            is Resource.Error ->
                showError(hero.message!!)
        }
    }

    private suspend fun updateView(heroCards: List<HeroCard>) {
        _state.update {
            it.copy(
                listHero = heroCards,
                backgroundColor = heroCards[it.currentIndex].color,
                getDataIsSuccessful = true
            )
        }
    }

    private fun showError(error: String) {
        _state.update {
            it.copy(
                getDataIsSuccessful = false,
                errorToLoadData = error
            )
        }
    }
}
