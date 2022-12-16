package gruzdev.artem.marvelapp.network.model

import androidx.annotation.Keep

@Keep
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)
