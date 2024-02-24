package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.application.interfaces.MovieApplicationInterface
import br.com.api.moviefinder.domain.model.Movie
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component

@Component
class MongoAdapter(
    private val movieRepository: MovieRepository
): MovieApplicationInterface {

    override suspend fun findAll(): List<Movie> =
        movieRepository.findAll().collectList().awaitSingleOrNull() ?: emptyList()

    override suspend fun findByImdbID(imdbID: String): Movie? =
        movieRepository.findByImdbID(imdbID).awaitSingleOrNull()

    override suspend fun save(movie: Movie): Movie? =
        movieRepository.save(movie).awaitSingleOrNull()

    override suspend fun deleteByImdbID(imdbID: String): Movie? =
        movieRepository.deleteByImdbID(imdbID).awaitSingleOrNull()
}