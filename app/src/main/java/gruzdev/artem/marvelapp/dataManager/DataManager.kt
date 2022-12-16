package gruzdev.artem.marvelapp.dataManager

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

interface DataManager {
    suspend fun getNextHeroes(idLast: Int) : Resource<List<HeroCard>>
    suspend fun getHero(id: Int) : Resource<HeroInfo>
}
