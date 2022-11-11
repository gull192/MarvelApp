package gruzdev.artem.marvelapp.screens.persom_screen.di

import android.app.Activity
import gruzdev.artem.marvelapp.core.di.featureComponent
import gruzdev.artem.marvelapp.core.di.modules.ViewModelModule
import gruzdev.artem.marvelapp.core.di.scope.FeatureScope
import dagger.Component
import gruzdev.artem.marvelapp.core.di.findComponentDependencies
import gruzdev.artem.marvelapp.network.di.RetrofitModule
import gruzdev.artem.marvelapp.screens.persom_screen.PersonScreenViewModel
import gruzdev.artem.marvelapp.screens.select_person_screen.di.module.SelectPersonViewModelModule


internal val personScreenComponent = featureComponent<PersonScreenComponent, Activity> { activity ->
    DaggerPersonScreenComponent.factory().create(activity.findComponentDependencies())
}

@FeatureScope
@Component(
    modules = [ViewModelModule::class, SelectPersonViewModelModule::class, RetrofitModule::class],
    dependencies = [PersonScreenDependencies::class]
)
internal interface PersonScreenComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: PersonScreenDependencies): PersonScreenComponent
    }

    val personScreenViewModelFactory: PersonScreenViewModel.Factory
}
