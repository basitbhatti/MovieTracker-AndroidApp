package com.basitbhatti.movieapp.data.di

import com.basitbhatti.movieapp.data.remote.AuthApiService
import com.basitbhatti.movieapp.data.remote.MoviesApiService
import com.basitbhatti.movieapp.data.repository.AuthRepositoryImpl
import com.basitbhatti.movieapp.data.repository.MoviesRepositoryImpl
import com.basitbhatti.movieapp.domain.repository.AuthRepository
import com.basitbhatti.movieapp.domain.repository.MoviesRepository
import com.basitbhatti.movieapp.domain.use_cases.GetPopularMovieListUseCase
import com.basitbhatti.movieapp.domain.use_cases.GetRequestTokenUseCase
import com.basitbhatti.movieapp.domain.use_cases.GetSessionUseCase
import com.basitbhatti.movieapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesApiService(retrofit: Retrofit): MoviesApiService {
        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(api: AuthApiService): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api : MoviesApiService) : MoviesRepository{
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetRequestTokenUseCase(repository: AuthRepository): GetRequestTokenUseCase {
        return GetRequestTokenUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSessionUseCase(repository: AuthRepository): GetSessionUseCase {
        return GetSessionUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePopularMoviesUseCase(repository: MoviesRepository): GetPopularMovieListUseCase {
        return GetPopularMovieListUseCase(repository)
    }

}