package gruzdev.artem.marvelapp.localSave.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gruzdev.artem.marvelapp.localSave.AppDatabase
import gruzdev.artem.marvelapp.localSave.HeroDao
import gruzdev.artem.marvelapp.localSave.LocalSaveRepository
import gruzdev.artem.marvelapp.localSave.LocalSaveRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(
       @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java, "database-hero"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) : HeroDao = db.heroDao()

    @Singleton
    @Provides
    fun provideLocalSaveRepository(dao: HeroDao) : LocalSaveRepository =
        LocalSaveRepositoryImpl(dao) as LocalSaveRepository
}