package br.com.api.moviefinder.application.interfaces

import br.com.api.moviefinder.domain.model.Movie

interface MovieInterface {

    suspend fun findAll(): List<Movie>

    suspend fun findByImdbID(imdbID: String): Movie

    suspend fun findByTitle(title: String): Movie
}