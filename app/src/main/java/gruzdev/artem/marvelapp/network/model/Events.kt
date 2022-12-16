package gruzdev.artem.marvelapp.network.model

import androidx.annotation.Keep

@Keep
data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
