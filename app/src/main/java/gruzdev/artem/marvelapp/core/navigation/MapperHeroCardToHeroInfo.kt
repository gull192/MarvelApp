package gruzdev.artem.marvelapp.core.navigation.model

import gruzdev.artem.marvelapp.core.model.HeroInfo
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard

fun HeroCard.asHeroInfo(): HeroInfo {
    return HeroInfo (
        id = id,
        heroName = title,
        photoUrl = photoURL,
        descriptionHero = descriptionHero
    )
}