package com.basitbhatti.movieapp.domain.repository

import com.basitbhatti.movieapp.domain.model.PopularMovieListResponseModel

interface MoviesRepository {

    suspend fun getPopularMovieList(page: Int): PopularMovieListResponseModel?

}