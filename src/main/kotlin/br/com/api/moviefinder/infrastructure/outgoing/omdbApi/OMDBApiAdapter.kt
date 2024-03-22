package br.com.api.moviefinder.infrastructure.outgoing.omdbApi

import br.com.api.moviefinder.application.interfaces.OMDBApiInterface
import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.infrastructure.outgoing.mappers.Mappers
import org.springframework.stereotype.Component

@Component
class OMDBApiAdapter(
    private val omdbApi: OMDBApi,
    private val mappers: Mappers
): OMDBApiInterface {
    override suspend fun findMovie(title: String): Movie =
        mappers.mapMovieEntityToMovie(omdbApi.findMovie(title))
}