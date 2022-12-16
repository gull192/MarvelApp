package gruzdev.artem.marvelapp.localSave.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class HeroInfoDb (
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "hero_name") var heroName: String,
    @ColumnInfo(name = "photo_url") var photoUrl: String,
    @ColumnInfo(name = "description_hero") var descriptionHero: String,
    @ColumnInfo(name = "hero_color") var color: String,
)
