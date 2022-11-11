package gruzdev.artem.marvelapp.network

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard

interface MarvelNetworkRepository {
    suspend fun getAllHeroes() : List<HeroCard>
    suspend fun getHeroInfo(characterId: Int) : HeroInfo
}