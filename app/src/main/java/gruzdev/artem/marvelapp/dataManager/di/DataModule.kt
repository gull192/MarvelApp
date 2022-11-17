package gruzdev.artem.marvelapp.dataManager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gruzdev.artem.marvelapp.dataManager.DataManager
import gruzdev.artem.marvelapp.dataManager.DataManagerImpl
import gruzdev.artem.marvelapp.localSave.LocalSaveRepository
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
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
