package br.com.api.moviefinder.application.interfaces

import br.com.api.moviefinder.domain.model.Movie

interface OMDBApiInterface {

    suspend fun findMovie(title: String): Movie
}