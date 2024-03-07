package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.domain.model.Movie
import org.springframework.stereotype.Component

@Component
class Mappers {

    fun mapApiMovieToMovieEntity(movie: Movie): Movie =
        movie.copy(id = null)
}