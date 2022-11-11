package gruzdev.artem.marvelapp.network

import gruzdev.artem.marvelapp.BuildConfig.PUBLIC_KEY_MARVEL
import gruzdev.artem.marvelapp.core.Hashing
import gruzdev.artem.marvelapp.network.model.MarvelAPI
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestInterface {

    @GET("/v1/public/characters?ts=1&apikey=${PUBLIC_KEY_MARVEL}")
    suspend fun getAllHeroes(@Query("hash") hash: String): MarvelAPI

    @GET("/v1/public/characters/{characterId}?ts=1&apikey=${PUBLIC_KEY_MARVEL}")
    suspend fun getHeroInfo(
        @Path("characterId") characterId: Int,
        @Query("hash") hash: String,
    ): MarvelAPI
}