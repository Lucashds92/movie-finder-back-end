package br.com.api.moviefinder.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(
    @Id
    val id: String? = null,
    val userName: String,
    val password: String,
    val favoriteMovies: List<MovieRepresentation>? = emptyList()
)
