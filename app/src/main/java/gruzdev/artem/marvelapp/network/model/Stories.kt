package gruzdev.artem.marvelapp.network.model

import androidx.annotation.Keep

@Keep
data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)
