package gruzdev.artem.marvelapp.localSave.model

import androidx.compose.ui.graphics.Color
import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

fun HeroInfoDb.asHeroInfo(): HeroInfo = HeroInfo(
    id = id,
    photoUrl = photoUrl,
    heroName = heroName,
    descriptionHero = descriptionHero
)

fun HeroInfoDb.asHeroCard(): HeroCard = HeroCard(
    id = id,
    photoURL = photoUrl,
    title = heroName,
    descriptionHero = descriptionHero,
    color = Color(color.toULong())
)
fun HeroCard.asHeroInfoDb(): HeroInfoDb = HeroInfoDb(
    id = id,
    heroName = title,
    color = color.value.toString(),
    descriptionHero = descriptionHero,
    photoUrl = photoURL
)

fun HeroCard.asHeroInfo(): HeroInfo = HeroInfo(
    id = id,
    photoUrl = photoURL,
    heroName = title,
    descriptionHero = descriptionHero
)



