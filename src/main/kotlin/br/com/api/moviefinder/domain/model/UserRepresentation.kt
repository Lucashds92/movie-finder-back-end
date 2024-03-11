package br.com.api.moviefinder.domain.model

data class UserRepresentation(
    val userName: String,
    val favoriteMovies: List<MovieRepresentation>? = emptyList()
) {
    constructor(user: User): this(
        userName = user.userName,
        favoriteMovies = user.favoriteMovies
    )
}
