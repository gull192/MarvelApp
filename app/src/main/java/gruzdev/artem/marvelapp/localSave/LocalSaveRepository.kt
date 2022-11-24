package gruzdev.artem.marvelapp.localSave

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

interface LocalSaveRepository {
    suspend fun getNextHeroes(idLast: Int) : Resource<List<HeroCard>>
    suspend fun getHero(id: Int) : Resource<HeroInfo>
    suspend fun insertAll(heroes: List<HeroCard>)
    suspend fun insertOne(heroCard: HeroCard)
}

