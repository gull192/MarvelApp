package gruzdev.artem.marvelapp.di

import android.app.Application
import gruzdev.artem.marvelapp.di.modules.ComponentDependenciesModule
import gruzdev.artem.marvelapp.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import gruzdev.artem.marvelapp.HeadlinesComposeApp
import gruzdev.artem.marvelapp.MainActivity
import gruzdev.artem.marvelapp.core.di.CommonDependencies
import gruzdev.artem.marvelapp.core.di.scope.AppScope
import gruzdev.artem.marvelapp.main.di.DrawerModule
import gruzdev.artem.marvelapp.main.di.MainComponentDependencies
import gruzdev.artem.marvelapp.network.di.RetrofitModule
import gruzdev.artem.marvelapp.screens.persom_screen.di.PersonScreenDependencies
import gruzdev.artem.marvelapp.screens.select_person_screen.di.SelectPersonDependencies

@AppScope
@Component(
    modules = [
        AppModule::class,
        ComponentDependenciesModule::class,
        DrawerModule::class,
    ]
)
interface AppComponent :
    CommonDependencies,
    MainComponentDependencies,
    PersonScreenDependencies,
    SelectPersonDependencies
{

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(app: HeadlinesComposeApp)
    fun inject(mainActivity: MainActivity)
}
