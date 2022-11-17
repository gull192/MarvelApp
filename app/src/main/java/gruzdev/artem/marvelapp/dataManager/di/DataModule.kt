package gruzdev.artem.marvelapp.dataManager.di

import dagger.Module
import dagger.Provides
import gruzdev.artem.marvelapp.core.di.scope.AppScope
import gruzdev.artem.marvelapp.dataManager.DataManager
import gruzdev.artem.marvelapp.dataManager.DataManagerImpl
import gruzdev.artem.marvelapp.localSave.LocalSaveRepository
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository

@Module
class DataModule {
    @AppScope
    @Provides
    fun provideDataManager(
        localSave: LocalSaveRepository,
        networkRepository: MarvelNetworkRepository
    ) =
        DataManagerImpl(
            networkRepository = networkRepository,
            localSaveRepository = localSave
        ) as DataManager
}