package br.com.api.moviefinder.domain.model

data class User(
    val id: String? = null,
    val userName: String,
    val password: String,
    val favoriteMovies: List<MovieRepresentation>? = emptyList()
)