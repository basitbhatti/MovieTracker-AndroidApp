package com.basitbhatti.movieapp.domain.model

data class PopularMovieListResponseModel(
    val page: Int? = 0,
    val results: List<ResultModel?>? = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)