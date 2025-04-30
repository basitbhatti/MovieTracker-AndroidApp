package com.basitbhatti.movieapp.data.repository

import android.util.Log
import com.basitbhatti.movieapp.data.remote.AuthApiService
import com.basitbhatti.movieapp.domain.model.PopularMovieListResponseModel
import com.basitbhatti.movieapp.domain.model.RequestTokenModel
import com.basitbhatti.movieapp.domain.model.RequestTokenResponseModel
import com.basitbhatti.movieapp.domain.model.SessionResponseModel
import com.basitbhatti.movieapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val api: AuthApiService) : AuthRepository {

    override suspend fun getRequestToken(): RequestTokenResponseModel? {
        val response = api.getRequestToken().body()?.toDomain()
        if (api.getRequestToken().isSuccessful) {
            Log.d("TAGTOKEN", "TokenRepositoryImpl success: $response")
        } else {
            Log.d("TAGTOKEN", "TokenRepositoryImpl not : $response")
        }
        return response
    }

    override suspend fun getSession(token: RequestTokenModel): SessionResponseModel? {
        val response = api.getSession(token)
        if (response.isSuccessful) {
            Log.d("TAGTOKEN", "SessionImpl success: $response")
        } else {
            Log.d("TAGTOKEN", "SessionImpl not : $response")
        }

        return response.body()?.toDomain()
    }
}