package com.basitbhatti.movieapp.data.repository

import com.basitbhatti.movieapp.data.remote.MoviesApiService
import com.basitbhatti.movieapp.domain.model.PopularMovieListResponseModel
import com.basitbhatti.movieapp.domain.repository.MoviesRepository

class MoviesRepositoryImpl(val moviesApiService: MoviesApiService) : MoviesRepository {
    override suspend fun getPopularMovieList(page: Int): PopularMovieListResponseModel? {
        return moviesApiService.getPopularMovies(page).body()?.toDomain()
    }
}