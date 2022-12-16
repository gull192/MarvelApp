package gruzdev.artem.marvelapp.network

import androidx.annotation.Keep
import gruzdev.artem.marvelapp.BuildConfig.PUBLIC_KEY_MARVEL
import gruzdev.artem.marvelapp.network.model.MarvelAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@Keep
interface QuestInterface {

    @GET("/v1/public/characters?ts=1&apikey=${PUBLIC_KEY_MARVEL}")
    suspend fun getNextHeroes(
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): Response<MarvelAPI>

    @GET("/v1/public/characters/{characterId}?ts=1&apikey=${PUBLIC_KEY_MARVEL}")
    suspend fun getHeroInfo(
        @Path("characterId") characterId: Int,
        @Query("hash") hash: String,
    ): Response<MarvelAPI>
}
