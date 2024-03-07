package br.com.api.moviefinder.application.exceptions

import java.time.LocalDateTime

data class ErrorView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int?,
    val error: String?,
    val message: String?
)
