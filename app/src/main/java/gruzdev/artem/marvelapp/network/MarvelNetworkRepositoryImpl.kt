package gruzdev.artem.marvelapp.network

import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color
import gruzdev.artem.marvelapp.BuildConfig.PRIVATE_KEY_MARVEL
import gruzdev.artem.marvelapp.core.Hashing
import gruzdev.artem.marvelapp.BuildConfig.PUBLIC_KEY_MARVEL
import gruzdev.artem.marvelapp.core.repositore.network.BaseRepo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.network.model.MarvelAPI
import gruzdev.artem.marvelapp.network.model.Result
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import kotlin.random.Random

@Keep
class MarvelNetworkRepositoryImpl constructor(private val quest: QuestInterface) :
    MarvelNetworkRepository,
    BaseRepo() {
    override suspend fun getNextHeroes(offset: Int): Resource<List<HeroCard>> {
        val hash = Hashing.md5("${1}${PRIVATE_KEY_MARVEL}${PUBLIC_KEY_MARVEL}")
        val response: Resource<MarvelAPI> = safeApiCall {
            quest.getNextHeroes(
                hash = hash,
                offset = offset
            )
        }
        val res: List<Result>? = response.data?.data?.results
        var error = response.message
        if (error == null) error = "I broke here"
        val rand = Random(System.currentTimeMillis())
        val data = res?.map {
            HeroCard(
                id = it.id,
                title = it.name,
                descriptionHero = it.description,
                photoURL = "${it.thumbnail.path}.${it.thumbnail.extension}",
                color = Color(rand.nextLong(0xFFFFFFFF)) // max value for color
            )
        }
        return if (data == null) Resource.Error(error) else Resource.Success(data)
    }

    override suspend fun getHeroInfo(characterId: Int): Resource<HeroCard> {
        val hash = Hashing.md5("${1}${PRIVATE_KEY_MARVEL}${PUBLIC_KEY_MARVEL}")
        val rand = Random(System.currentTimeMillis())
        val response: Resource<MarvelAPI> =
            safeApiCall { quest.getHeroInfo(hash = hash, characterId = characterId) }
        val res: List<Result>? =
            response.data?.data?.results
        val data = res?.get(0)?.let {
            HeroCard(
                id = it.id,
                title = it.name,
                photoURL = "${it.thumbnail.path}.${it.thumbnail.extension}",
                descriptionHero = it.description,
                color = Color(rand.nextLong(0xFFFFFFFF))  // max value for color
            )
        }
        val error = response.message
        return if (data == null) Resource.Error(error!!) else Resource.Success(data)
    }
}
