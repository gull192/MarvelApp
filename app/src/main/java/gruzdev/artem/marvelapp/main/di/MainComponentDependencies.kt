package gruzdev.artem.marvelapp.main.di

import dagger.Module
import dagger.Provides
import gruzdev.artem.marvelapp.core.di.CommonDependencies
import gruzdev.artem.marvelapp.core.di.ContentViewSetter

interface MainComponentDependencies : CommonDependencies {
    fun provideContentViewSetter(): ContentViewSetter
}


@Module
interface DrawerModule {

    companion object {
        @Provides
        fun provideContentViewSetter(): ContentViewSetter = ContentViewSetter { activity, view ->
            activity.setContentView(view)
        }
    }
}
