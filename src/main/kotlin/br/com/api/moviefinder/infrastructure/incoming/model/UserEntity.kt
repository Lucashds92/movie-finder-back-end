package br.com.api.moviefinder.infrastructure.incoming.model

import br.com.api.moviefinder.domain.model.MovieRepresentation
import br.com.api.moviefinder.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class UserEntity(
    @Id
    val id: String? = null,
    val userName: String,
    val password: String,
    val favoriteMovies: List<MovieRepresentation>? = emptyList()
) {
    constructor(user: User): this(
        id = user.id,
        userName = user.userName,
        password = user.password,
        favoriteMovies = user.favoriteMovies
    )
}

