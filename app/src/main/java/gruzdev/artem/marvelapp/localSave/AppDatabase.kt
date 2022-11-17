package gruzdev.artem.marvelapp.localSave

import androidx.room.Database
import androidx.room.RoomDatabase
import gruzdev.artem.marvelapp.localSave.model.HeroInfoDb

@Database(entities = [HeroInfoDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao() : HeroDao
}