package gruzdev.artem.marvelapp.screens.selectPersonScreen.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gruzdev.artem.marvelapp.core.di.viewmodel.DaggerViewModelFactory
import dagger.Module
import dagger.Provides
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
