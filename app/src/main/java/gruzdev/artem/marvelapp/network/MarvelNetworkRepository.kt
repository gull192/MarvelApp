package gruzdev.artem.marvelapp.network

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

interface MarvelNetworkRepository {
    suspend fun getAllHeroes() : Resource<List<HeroCard>>
    suspend fun getHeroInfo(characterId: Int) : Resource<HeroCard>
}

