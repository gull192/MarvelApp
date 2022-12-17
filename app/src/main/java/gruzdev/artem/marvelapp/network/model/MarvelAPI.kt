package gruzdev.artem.marvelapp.network.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class MarvelAPI(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    @Json(name="`data`")
    val data: Data,
    val etag: String,
    val status: String
)
