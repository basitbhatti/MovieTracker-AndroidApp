package com.basitbhatti.movieapp.data.dto

import com.basitbhatti.movieapp.domain.model.RequestTokenResponseModel

class RequestTokenResponseDTO(
    val expires_at: String? = "",
    val request_token: String? = "",
    val success: Boolean? = false
) {
    fun toDomain(): RequestTokenResponseModel = RequestTokenResponseModel(expires_at, request_token, success)
}