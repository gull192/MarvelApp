package gruzdev.artem.marvelapp.network.di

import dagger.Module
import dagger.Provides
import gruzdev.artem.marvelapp.core.di.scope.AppScope
import gruzdev.artem.marvelapp.core.di.scope.FeatureScope
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
import gruzdev.artem.marvelapp.network.MarvelNetworkRepositoryImpl
import gruzdev.artem.marvelapp.network.QuestInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RetrofitModule {

    @AppScope
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @AppScope
    @Provides
    fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @AppScope
    @Provides
    fun marvelRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @AppScope
    @Provides
    fun provideQuestApi(retrofit: Retrofit): QuestInterface =
        retrofit.create(QuestInterface::class.java)

    @AppScope
    @Provides
    fun provideMarvelRepoImpl(questInterface: QuestInterface) =
        MarvelNetworkRepositoryImpl(questInterface)

    @AppScope
    @Provides
    fun provideMarvelRepo(marvelNetworkRepositoryImpl: MarvelNetworkRepositoryImpl) =
        marvelNetworkRepositoryImpl as MarvelNetworkRepository
}