package gruzdev.artem.marvelapp.screens.personScreen.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gruzdev.artem.marvelapp.core.di.viewmodel.DaggerViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
internal interface PersonScreenViewModelModule {

    companion object {
        @Provides
        fun providePersonScreenViewModelFactory(
            creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory = DaggerViewModelFactory(creators)
    }
}
