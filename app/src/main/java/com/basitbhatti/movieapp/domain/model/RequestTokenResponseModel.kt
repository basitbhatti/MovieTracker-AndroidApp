package com.basitbhatti.movieapp.domain.model

data class RequestTokenResponseModel(
    val expires_at: String? = "",
    val request_token: String? = "",
    val success: Boolean? = false
)