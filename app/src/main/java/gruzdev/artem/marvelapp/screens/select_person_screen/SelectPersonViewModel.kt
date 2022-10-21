package gruzdev.artem.marvelapp.screens.select_person_screen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.screens.persom_screen.model.HeroInfo
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import gruzdev.artem.marvelapp.ui.theme.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SelectPersonViewModel : ViewModel() {
    private val _state = MutableStateFlow(SelectPersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SelectPersonUIEffect>()
    val effect = _effect.asSharedFlow()

    init {
        Log.e("INIT", "init view model")
        _state.update {
            it.copy(
                listHero = listOf(
                    HeroCard(
                        id = 0,
                        image = R.drawable.halk_sample,
                        title = "Halk",
                        color = Color.Black,
                        photoURL = "1"
                    ),
                    HeroCard(
                        id = 0,
                        image = R.drawable.iron_man,
                        title = "Iron man",
                        color = Purple500,
                        photoURL = "1"
                    ),
                    HeroCard(
                        id = 0,
                        image = R.drawable.doctor_strange,
                        title = "Doctor strange",
                        color = Purple700,
                        photoURL = "1"
                    ),
                    HeroCard(
                        id = 0,
                        image = R.drawable.spider_man,
                        title = "Spider_man",
                        color = Teal200,
                        photoURL = "1"
                    ),
                    HeroCard(
                        id = 0,
                        image = R.drawable.capitain_america,
                        title = "Captain america",
                        color = Purple200,
                        photoURL = "1"
                    ),
                    HeroCard(
                        id = 0,
                        image = R.drawable.deadpoll,
                        title = "Deadpoll",
                        color = Purple200,
                        photoURL = ""
                    ),
                )
            )
        }
        _state.update {
            it.copy(backgroundColor = state.value.listHero[0].color)
        }
    }

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
        val heroInfo = heroCard.let { HeroInfo(
            id = it.id,
            photoUrl = it.photoURL
        ) }
        _effect.emit(SelectPersonUIEffect.NavigateToPersonScreen(heroInfo))
    }
}
