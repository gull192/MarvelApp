package gruzdev.artem.marvelapp.screens.select_person_screen.di

import android.app.Activity
import gruzdev.artem.marvelapp.core.di.featureComponent
import gruzdev.artem.marvelapp.core.di.findComponentDependencies
import gruzdev.artem.marvelapp.core.di.modules.ViewModelModule
import gruzdev.artem.marvelapp.core.di.scope.FeatureScope
import dagger.Component
import gruzdev.artem.marvelapp.network.di.RetrofitModule
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonViewModel
import gruzdev.artem.marvelapp.screens.select_person_screen.di.module.SelectPersonViewModelModule


internal val selectPersonComponent = featureComponent<SelectPersonComponent, Activity> { activity ->
    DaggerSelectPersonComponent.factory().create(activity.findComponentDependencies())
}

@FeatureScope
@Component(
    modules = [ViewModelModule::class, SelectPersonViewModelModule::class, RetrofitModule::class],
    dependencies = [SelectPersonDependencies::class]
)
internal interface SelectPersonComponent {

    @Component.Factory
    interface Factory {
        fun create(deps: SelectPersonDependencies): SelectPersonComponent
    }

    val selectPersonViewModelFactory: SelectPersonViewModel.Factory
}
