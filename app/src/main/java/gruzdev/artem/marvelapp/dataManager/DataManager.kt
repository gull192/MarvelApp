package gruzdev.artem.marvelapp.dataManager

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

interface DataManager {
    suspend fun getAll() : List<HeroCard>
    suspend fun getHero(id: Int) : HeroInfo
}