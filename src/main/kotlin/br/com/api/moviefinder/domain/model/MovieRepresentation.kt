package br.com.api.moviefinder.domain.model

data class MovieRepresentation(
    val imdbID: String?,
    val title: String?
) {
    constructor(movie: Movie) : this(
        imdbID = movie.imdbID,
        title = movie.title
    )
}
