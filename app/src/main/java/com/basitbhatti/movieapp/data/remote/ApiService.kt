package com.basitbhatti.movieapp.data.remote

import com.basitbhatti.movieapp.BuildConfig
import com.basitbhatti.movieapp.domain.model.RequestTokenResponseModel
import com.basitbhatti.movieapp.utils.REQUEST_TOKEN_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "accept: application/json", "Authorization: Bearer " + BuildConfig.BEARER_TOKEN
    )
    @GET(REQUEST_TOKEN_ENDPOINT)
    suspend fun getRequestToken(
    ): Response<RequestTokenResponseModel>


}