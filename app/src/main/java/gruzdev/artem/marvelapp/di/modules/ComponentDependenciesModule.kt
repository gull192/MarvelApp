package gruzdev.artem.marvelapp.di.modules


import gruzdev.artem.marvelapp.main.di.MainComponentDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gruzdev.artem.marvelapp.core.di.ComponentDependencies
import gruzdev.artem.marvelapp.core.di.ComponentDependenciesKey
import gruzdev.artem.marvelapp.di.AppComponent
import gruzdev.artem.marvelapp.screens.persom_screen.di.PersonScreenDependencies
import gruzdev.artem.marvelapp.screens.select_person_screen.di.SelectPersonDependencies

@Module
interface ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainComponentDependencies::class)
    fun bindMainComponentDeps(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(PersonScreenDependencies::class)
    fun bindPersonScreenComponentDeps(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SelectPersonDependencies::class)
    fun bindSelectPersonComponentDeps(appComponent: AppComponent): ComponentDependencies
}
