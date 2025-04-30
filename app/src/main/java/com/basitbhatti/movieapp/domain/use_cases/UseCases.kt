package com.basitbhatti.movieapp.domain.use_cases

import com.basitbhatti.movieapp.domain.model.PopularMovieListResponseModel
import com.basitbhatti.movieapp.domain.model.RequestTokenModel
import com.basitbhatti.movieapp.domain.model.RequestTokenResponseModel
import com.basitbhatti.movieapp.domain.model.SessionResponseModel
import com.basitbhatti.movieapp.domain.repository.AuthRepository
import com.basitbhatti.movieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(val repository: AuthRepository) {
    suspend operator fun invoke(): RequestTokenResponseModel? {
        return repository.getRequestToken()
    }
}

class GetSessionUseCase @Inject constructor(val repository: AuthRepository) {
    suspend operator fun invoke(token: RequestTokenModel): SessionResponseModel? {
        return repository.getSession(token)
    }
}

class GetPopularMovieListUseCase @Inject constructor(val repository: MoviesRepository) {
    suspend operator fun invoke(page:Int): PopularMovieListResponseModel? {
        return repository.getPopularMovieList(page)
    }
}