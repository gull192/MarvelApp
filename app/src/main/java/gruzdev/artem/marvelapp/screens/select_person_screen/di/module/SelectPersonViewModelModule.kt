package gruzdev.artem.marvelapp.screens.select_person_screen.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import gruzdev.artem.marvelapp.core.di.viewmodel.DaggerViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import gruzdev.artem.marvelapp.core.di.viewmodel.ViewModelKey
import gruzdev.artem.marvelapp.screens.select_person_screen.SelectPersonViewModel
import javax.inject.Provider

@Module
internal interface SelectPersonViewModelModule {

    companion object {
        @Provides
        fun provideSelectPersonViewModelFactory(
            creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory = DaggerViewModelFactory(creators)
    }

}
