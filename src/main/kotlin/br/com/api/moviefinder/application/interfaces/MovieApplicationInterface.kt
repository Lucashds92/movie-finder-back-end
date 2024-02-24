package br.com.api.moviefinder.application.interfaces

import br.com.api.moviefinder.domain.model.Movie

interface MovieApplicationInterface {

    suspend fun findAll(): List<Movie>

    suspend fun findByImdbID(imdbID: String): Movie?

    suspend fun save(movie: Movie): Movie?

    suspend fun deleteByImdbID(imdbID: String): Movie?
}