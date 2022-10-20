package gruzdev.artem.marvelapp.screens.select_person_screen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import gruzdev.artem.marvelapp.ui.theme.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SelectPersonViewModel : ViewModel() {
    private val _state = MutableStateFlow(SelectPersonUIState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SelectPersonUIEffect>()
    val effect = _effect.asSharedFlow()

    init{
        Log.e("INIT", "init view model")
        _state.update {
            it.copy(
            listHero = listOf(
                HeroCard(id = 0,image =  R.drawable.halk_sample, title = "Halk", color = Color.Black ),
                HeroCard(id = 0,image =  R.drawable.iron_man, title = "Iron man", color = Purple500),
                HeroCard(id = 0,image =  R.drawable.doctor_strange, title = "Doctor strange", color = Purple700),
                HeroCard(id = 0,image =  R.drawable.spider_man, title = "Spider_man", color = Teal200),
                HeroCard(id = 0,image =  R.drawable.capitain_america, title = "Captain america", color = Purple200),
                HeroCard(id = 0,image =  R.drawable.deadpoll, title = "Deadpoll", color = Purple200),
            )
        )
        }
        _state.update {
            it.copy( backgroundColor = state.value.listHero[0].color)
        }
    }
    fun sendEvent (event: SelectPersonUIEvent) {
        when (event) {
            is SelectPersonUIEvent.OnCurrentIndexChange -> {
                viewModelScope.launch {
                    changeCurrentItem(event.newIndex)
                }
            }
            SelectPersonUIEvent.OnclickHero -> TODO()
        }
    }

    private fun changeCurrentItem(newIndex: Int) {
        Log.e("TAF", newIndex.toString())
        _state.update {
            it.copy( currentIndex = newIndex)
        }
        _state.update {
            it.copy(backgroundColor = state.value.listHero[newIndex].color)
        }
        Log.e("COLOR",_state.value.backgroundColor.toString() )
    }
}