package gruzdev.artem.marvelapp.localSave

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.core.repositore.network.Resource
import gruzdev.artem.marvelapp.localSave.model.asHeroCard
import gruzdev.artem.marvelapp.localSave.model.asHeroInfo
import gruzdev.artem.marvelapp.localSave.model.asHeroInfoDb
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

class LocalSaveRepositoryImpl(private val dao: HeroDao) : LocalSaveRepository {
    override suspend fun getNextHeroes(idLast: Int): Resource<List<HeroCard>> {
        val res = dao.getNextHero(idLast).map {
            it.asHeroCard()
        }
        return if (res.isEmpty()) {
            Resource.Error(
                "you are not connected to the internet" +
                        " and you do not have a local save." +
                        " Please check your internet connection"
            )
        } else {
            Resource.Success(res)
        }
    }

    override suspend fun getHero(id: Int): Resource<HeroInfo> {
        val res =  dao.getHero(id)?.asHeroInfo()
        return if (res == null) {
            Resource.Error(
                "Id not founded :("
            )
        } else {
            Resource.Success(res)
        }
    }

    override suspend fun insertAll(heroes: List<HeroCard>) {
        dao.insertAll(
            heroes.map { it.asHeroInfoDb() }
        )
    }

    override suspend fun insertOne(heroInfo: HeroCard) {
        dao.insertOne(
            heroInfo.asHeroInfoDb()
        )
    }
}
