package gruzdev.artem.marvelapp.screens.selectPersonScreen.di

import gruzdev.artem.marvelapp.core.di.CommonDependencies
import gruzdev.artem.marvelapp.dataManager.DataManager

interface SelectPersonDependencies: CommonDependencies {
    val dataManager: DataManager
}
