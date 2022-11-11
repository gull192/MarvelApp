package gruzdev.artem.marvelapp.main.di

import android.app.Activity
import dagger.Component
import gruzdev.artem.marvelapp.MainActivity
import gruzdev.artem.marvelapp.core.di.featureComponent
import gruzdev.artem.marvelapp.core.di.findComponentDependencies

val mainComponent = featureComponent<MainComponent, Activity> { activity ->
    DaggerMainComponent.factory().create(activity.findComponentDependencies())
}

@Component(
    dependencies = [MainComponentDependencies::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: MainComponentDependencies): MainComponent
    }

    fun inject(activity: MainActivity)
}
