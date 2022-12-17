package gruzdev.artem.marvelapp.network.model

import androidx.annotation.Keep

@Keep
data class Url(
    val type: String,
    val url: String
)
