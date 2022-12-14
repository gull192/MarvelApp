package gruzdev.artem.marvelapp.core.navigation

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

fun HeroCard.asHeroInfo(): HeroInfo {
    return HeroInfo (
        id = id,
        heroName = title,
        photoUrl = photoURL,
        descriptionHero = descriptionHero
    )
}
