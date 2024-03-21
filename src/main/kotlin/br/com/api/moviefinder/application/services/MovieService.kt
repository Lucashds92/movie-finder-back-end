package br.com.api.moviefinder.application.services

import br.com.api.moviefinder.application.interfaces.MovieInterface
import br.com.api.moviefinder.application.interfaces.OMDBApiInterface
import br.com.api.moviefinder.domain.model.Movie

class MovieService(
    private val movieInterface: MovieInterface,
    private val omdbApiInterface: OMDBApiInterface
) {

    suspend fun getMovieById(imdbID: String): Movie =
        movieInterface.findByImdbID(imdbID)

    suspend fun findMovie(title: String): Movie {
        return movieInterface.findByTitle(title)
    }
}