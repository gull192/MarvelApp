package gruzdev.artem.marvelapp.screens.select_person_screen.model

import android.provider.ContactsContract.DisplayPhoto
import androidx.compose.ui.graphics.Color

data class HeroCard(
    val id: Int, //вместо int сделать другой индификатор для api
    val image: Int,
    val title: String,
    val color: Color,
    val photoURL: String
)
