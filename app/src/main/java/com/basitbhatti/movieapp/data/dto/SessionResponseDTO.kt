package com.basitbhatti.movieapp.data.dto

import com.basitbhatti.movieapp.domain.model.SessionResponseModel

class SessionResponseDTO(
    val session_id: String? = "",
    val success: Boolean? = false
) {
    fun toDomain() : SessionResponseModel = SessionResponseModel(session_id, success)
}