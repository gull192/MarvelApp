package gruzdev.artem.marvelapp.network

import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

interface MarvelNetworkRepository {
    suspend fun getNextHeroes(offset: Int) : Resource<List<HeroCard>>
    suspend fun getHeroInfo(characterId: Int) : Resource<HeroCard>
}

