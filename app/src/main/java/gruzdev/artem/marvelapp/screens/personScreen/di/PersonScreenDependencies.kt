package gruzdev.artem.marvelapp.screens.personScreen.di

import gruzdev.artem.marvelapp.core.di.CommonDependencies
import gruzdev.artem.marvelapp.dataManager.DataManager

interface PersonScreenDependencies: CommonDependencies {
    val dataManager: DataManager
}
