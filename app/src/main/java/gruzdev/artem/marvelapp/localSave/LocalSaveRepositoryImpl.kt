package gruzdev.artem.marvelapp.localSave

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.localSave.model.asHeroCard
import gruzdev.artem.marvelapp.localSave.model.asHeroInfo
import gruzdev.artem.marvelapp.localSave.model.asHeroInfoDb
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import javax.inject.Inject

class LocalSaveRepositoryImpl(private val dao: HeroDao) : LocalSaveRepository {
    override suspend fun getAll(): List<HeroCard> =
        dao.getAllHero().map {
        it.asHeroCard()
    }

    override suspend fun getHero(id: Int): HeroInfo = dao.getHero(id).asHeroInfo()

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