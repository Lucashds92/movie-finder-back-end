package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.application.exceptions.NotFoundException
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

    override suspend fun findByImdbID(imdbID: String): Movie =
        movieRepository.findByImdbID(imdbID)
            .awaitSingleOrNull() ?: throw NotFoundException("MongoAdapter::findByImdbID: Movie not found with IMDB ID: $imdbID")

    override suspend fun save(movie: Movie): Movie? =
        movieRepository.save(movie)
            .awaitSingleOrNull() ?: throw NotFoundException("MongoAdapter::save: Could not save the Movie with IMDB ID: ${movie.imdbID}")

    override suspend fun deleteByImdbID(imdbID: String): Movie? =
        movieRepository.deleteByImdbID(imdbID)
            .awaitSingleOrNull() ?: throw NotFoundException("MongoAdapter::deleteByImdbID: Movie not found with IMDB ID: $imdbID")
}