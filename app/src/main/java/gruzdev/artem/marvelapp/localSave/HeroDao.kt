package gruzdev.artem.marvelapp.localSave

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gruzdev.artem.marvelapp.localSave.model.HeroInfoDb

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero")
    suspend fun getAllHero() : List<HeroInfoDb>

    @Query("SELECT * FROM hero WHERE id = :id")
    suspend fun getHero(id: Int) : HeroInfoDb

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(heroes: List<HeroInfoDb>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOne(hero: HeroInfoDb)
}
