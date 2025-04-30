package com.basitbhatti.movieapp.domain.repository

import com.basitbhatti.movieapp.domain.model.PopularMovieListResponseModel
import com.basitbhatti.movieapp.domain.model.RequestTokenModel
import com.basitbhatti.movieapp.domain.model.RequestTokenResponseModel
import com.basitbhatti.movieapp.domain.model.SessionResponseModel

interface AuthRepository {

    suspend fun getRequestToken(): RequestTokenResponseModel?

    suspend fun getSession(token: RequestTokenModel): SessionResponseModel?

}