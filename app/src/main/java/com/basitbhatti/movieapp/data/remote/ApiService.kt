package com.basitbhatti.movieapp.data.remote

import com.basitbhatti.movieapp.BuildConfig
import com.basitbhatti.movieapp.data.dto.PopularMovieListResponseDTO
import com.basitbhatti.movieapp.data.dto.RequestTokenResponseDTO
import com.basitbhatti.movieapp.data.dto.SessionResponseDTO
import com.basitbhatti.movieapp.domain.model.RequestTokenModel
import com.basitbhatti.movieapp.utils.POPULAR_MOVIES_ENDPOINT
import com.basitbhatti.movieapp.utils.REQUEST_TOKEN_ENDPOINT
import com.basitbhatti.movieapp.utils.SESSION_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface AuthApiService {

    @Headers(
        "accept: application/json", "Authorization: Bearer " + BuildConfig.BEARER_TOKEN
    )
    @GET(REQUEST_TOKEN_ENDPOINT)
    suspend fun getRequestToken(): Response<RequestTokenResponseDTO>

    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "Authorization: Bearer " + BuildConfig.BEARER_TOKEN
    )
    @GET(SESSION_ENDPOINT)
    suspend fun getSession(@Body token: RequestTokenModel): Response<SessionResponseDTO>
}


interface MoviesApiService {

    @Headers(
        "accept: application/json",
        "Authorization: Bearer " + BuildConfig.BEARER_TOKEN
    )
    @GET(POPULAR_MOVIES_ENDPOINT)
    suspend fun getPopularMovies(@Query("page") page: Int): Response<PopularMovieListResponseDTO>

}