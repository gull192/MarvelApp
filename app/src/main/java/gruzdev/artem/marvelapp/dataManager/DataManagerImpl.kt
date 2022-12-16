package gruzdev.artem.marvelapp.dataManager

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.localSave.LocalSaveRepository
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

class DataManagerImpl(
    private val networkRepository: MarvelNetworkRepository,
    private val localSaveRepository: LocalSaveRepository
) : DataManager {
    override suspend fun getNextHeroes(idLast: Int): Resource<List<HeroCard>> {
        when (val hero : Resource<List<HeroCard>> = networkRepository.getNextHeroes(idLast)) {
            is Resource.Success ->
                localSaveRepository.insertAll(hero.data!!)
            else -> {}
        }
        return localSaveRepository.getNextHeroes(idLast)
    }

    override suspend fun getHero(id: Int): Resource<HeroInfo> {
        when (val hero : Resource<HeroCard> = networkRepository.getHeroInfo(id)) {
            is Resource.Success ->
                localSaveRepository.insertOne(hero.data!!)
            else -> {}
        }
        return localSaveRepository.getHero(id)
    }

}
