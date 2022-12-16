package gruzdev.artem.marvelapp.screens.selectPersonScreen

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gruzdev.artem.marvelapp.core.repositore.paging.DefaultPaginator
import gruzdev.artem.marvelapp.dataManager.DataManager
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import gruzdev.artem.marvelapp.ui.theme.Dune
import gruzdev.artem.marvelapp.ui.theme.MarvelAppTheme
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

    private lateinit var paginator: DefaultPaginator<Int, HeroCard>

    private var isFirstLoad = true

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
                    if (isFirstLoad) {
                        _state.update {
                            SelectPersonUIState.Loading
                        }
                        initPaginator()
                        paginator.loadNextItems()
                        isFirstLoad = false
                    }
                }

            SelectPersonUIEvent.OnLoadPagingData ->
                viewModelScope.launch {
                    paginator.loadNextItems()
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

    private fun setError(error: String) {
        _state.update {
            SelectPersonUIState.Error(error)
        }
    }

    private fun initPaginator() {

        paginator = DefaultPaginator(
            initKey = castToDisplayState().currentOffset,
            onLoadUpdated = { isLoading ->
                if (_state.value is SelectPersonUIState.DisplayHeroes) {
                    _state.update {
                        castToDisplayState().copy(isLoading = isLoading)
                    }
                }
            },
            getNextKey = {
                castToDisplayState().currentOffset + 20
            },
            onError = { error ->
                Log.e("!!!",error )
                setError(error)
            },
            onSuccess = { items, newKey ->
                val displayState = castToDisplayState()
                _state.update {
                    displayState.copy(
                        listHero = displayState.listHero + items,
                        currentOffset = newKey,
                        endReached = items.isEmpty(),
                        backgroundColor = if (displayState.listHero.isNotEmpty()) {
                            displayState.listHero[displayState.currentIndex].color
                        } else {
                            Dune
                        }
                    )
                }
            },
            onRequest = {
                dataManager.getNextHeroes(it)
            }
        )
    }

    private inline fun castToDisplayState(): SelectPersonUIState.DisplayHeroes =
        if (_state.value is SelectPersonUIState.DisplayHeroes)
            _state.value as SelectPersonUIState.DisplayHeroes
        else SelectPersonUIState.DisplayHeroes()
}
