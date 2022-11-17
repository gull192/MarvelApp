package gruzdev.artem.marvelapp.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gruzdev.artem.marvelapp.network.MarvelNetworkRepository
import gruzdev.artem.marvelapp.network.MarvelNetworkRepositoryImpl
import gruzdev.artem.marvelapp.network.QuestInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun marvelRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Singleton
    @Provides
    fun provideQuestApi(retrofit: Retrofit): QuestInterface =
        retrofit.create(QuestInterface::class.java)

    @Singleton
    @Provides
    fun provideMarvelRepoImpl(questInterface: QuestInterface) =
        MarvelNetworkRepositoryImpl(questInterface)

    @Singleton
    @Provides
    fun provideMarvelRepo(marvelNetworkRepositoryImpl: MarvelNetworkRepositoryImpl) =
        marvelNetworkRepositoryImpl as MarvelNetworkRepository
}