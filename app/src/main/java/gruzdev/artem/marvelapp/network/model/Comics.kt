package gruzdev.artem.marvelapp.network.model

import androidx.annotation.Keep

@Keep
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
