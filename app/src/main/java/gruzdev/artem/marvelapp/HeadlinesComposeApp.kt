package gruzdev.artem.marvelapp

import android.app.Application
import gruzdev.artem.marvelapp.core.di.ComponentDependenciesProvider
import gruzdev.artem.marvelapp.core.di.HasComponentDependencies
import gruzdev.artem.marvelapp.di.DaggerAppComponent
import javax.inject.Inject

open class HeadlinesComposeApp : Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(this)
            .inject(this)

    }

}
