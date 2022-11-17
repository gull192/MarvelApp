package gruzdev.artem.marvelapp.localSave.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import gruzdev.artem.marvelapp.core.di.scope.AppScope
import gruzdev.artem.marvelapp.localSave.AppDatabase
import gruzdev.artem.marvelapp.localSave.HeroDao
import gruzdev.artem.marvelapp.localSave.LocalSaveRepository
import gruzdev.artem.marvelapp.localSave.LocalSaveRepositoryImpl

@Module
class RoomModule {

    @AppScope
    @Provides
    fun provideRoom(app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java, "database-hero"
    ).build()

    @AppScope
    @Provides
    fun provideDao(db: AppDatabase) : HeroDao = db.heroDao()

    @AppScope
    @Provides
    fun provideLocalSaveRepository(dao: HeroDao) : LocalSaveRepository =
        LocalSaveRepositoryImpl(dao) as LocalSaveRepository
}