package gruzdev.artem.marvelapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import gruzdev.artem.marvelapp.BuildConfig
import gruzdev.artem.marvelapp.core.di.InjectedKey
import gruzdev.artem.marvelapp.core.di.scope.AppScope
import javax.inject.Named

@Module
interface AppModule {

    companion object {

        @AppScope
        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @JvmStatic
        @Provides
        @Named(InjectedKey.Configuration.VERSION_NAME)
        fun provideAppVersionName(): String = BuildConfig.VERSION_NAME
    }
}
