package gruzdev.artem.marvelapp.network

import androidx.compose.ui.graphics.Color
import gruzdev.artem.marvelapp.BuildConfig.PRIVATE_KEY_MARVEL
import gruzdev.artem.marvelapp.core.Hashing
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.BuildConfig.PUBLIC_KEY_MARVEL
import gruzdev.artem.marvelapp.network.model.MarvelAPI
import gruzdev.artem.marvelapp.network.model.Result
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import kotlin.random.Random

class MarvelNetworkRepositoryImpl constructor(private val quest: QuestInterface) :
    MarvelNetworkRepository {
    override suspend fun getAllHeroes(): List<HeroCard> {
        val hash = Hashing.md5("${1}${PRIVATE_KEY_MARVEL}${PUBLIC_KEY_MARVEL}")
        val res: List<Result> = quest.getAllHeroes(hash).data.results
        val rand = Random(1828281)
        return res.map {
            HeroCard(
                id = it.id,
                title = it.name,
                descriptionHero = it.description,
                photoURL = "${it.thumbnail.path}.${it.thumbnail.extension}",
                color = Color(rand.nextLong(0xFFFFFFFF)) // пока будет случайный цвет, потом придумаую
            )
        }
    }

    override suspend fun getHeroInfo(characterId: Int): HeroInfo {
        val hash = Hashing.md5("${1}${PRIVATE_KEY_MARVEL}${PUBLIC_KEY_MARVEL}")
        val res: List<Result> =
            quest.getHeroInfo(hash = hash, characterId = characterId).data.results
        return HeroInfo(
            id = res[0].id,
            heroName = res[0].name,
            photoUrl = "${res[0].thumbnail.path}.${res[0].thumbnail.extension}",
            descriptionHero = res[0].description
        )
    }
}