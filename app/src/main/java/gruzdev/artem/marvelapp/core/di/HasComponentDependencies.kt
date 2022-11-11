package gruzdev.artem.marvelapp.core.di

import gruzdev.artem.marvelapp.core.di.ComponentDependenciesProvider

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}
