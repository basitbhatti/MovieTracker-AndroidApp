package com.basitbhatti.movieapp.data.dto

import com.basitbhatti.movieapp.domain.model.PopularMovieListResponseModel
import com.basitbhatti.movieapp.domain.model.ResultModel

data class PopularMovieListResponseDTO(
    val page: Int? = 0,
    val results: List<ResultModel?>? = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
) {
    fun toDomain():PopularMovieListResponseModel = PopularMovieListResponseModel(page, results, total_pages, total_results)
}