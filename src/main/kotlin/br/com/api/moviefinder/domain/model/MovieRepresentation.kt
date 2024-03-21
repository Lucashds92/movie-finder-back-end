package br.com.api.moviefinder.domain.model

data class MovieRepresentation(
    val imdbId: String?,
    val title: String?
) {
    constructor(movie: Movie) : this(
        imdbId = movie.imdbID,
        title = movie.title
    )
}
