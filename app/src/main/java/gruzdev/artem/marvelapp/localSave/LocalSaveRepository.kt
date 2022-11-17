package gruzdev.artem.marvelapp.localSave

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.localSave.model.HeroInfoDb
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

interface LocalSaveRepository {
    suspend fun getAll() : List<HeroCard>
    suspend fun getHero(id: Int) : HeroInfo
    suspend fun insertAll(heroes: List<HeroCard>)
    suspend fun insertOne(heroCard: HeroCard)
}