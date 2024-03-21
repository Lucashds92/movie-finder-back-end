package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.application.exceptions.NotFoundException
import br.com.api.moviefinder.application.interfaces.MovieInterface
import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.infrastructure.outgoing.mappers.Mappers
import br.com.api.moviefinder.infrastructure.outgoing.omdbApi.OMDBApi
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class MovieMongoAdapter(
    private val movieRepository: MovieRepository,
    private val omdbApi: OMDBApi,
    private val mappers: Mappers
) : MovieInterface {

    override suspend fun findAll(): List<Movie> {
        val movieEntityList = movieRepository.findAll().collectList().awaitSingleOrNull()

        val movieList = mutableListOf<Movie>()

        movieEntityList?.forEach { movie ->
            movieList.add(mappers.mapMovieEntityToMovie(movie))
        }

        return movieList
    }

    override suspend fun findByImdbID(imdbID: String): Movie {
        val movieEntity = movieRepository.findByImdbID(imdbID).awaitSingleOrNull()
            ?: throw NotFoundException("MongoAdapter::findByImdbID: Movie not found with IMDB ID: $imdbID")
        return mappers.mapMovieEntityToMovie(movieEntity)
    }

    override suspend fun findByTitle(title: String): Movie =
        mappers.mapMovieEntityToMovie(
            movieRepository.findByTitle(formatTitleForDataBase(title))
                .awaitSingleOrNull()
                ?: omdbApi.findMovie(formatTitleForApi(title))
        )

    suspend fun formatTitleForDataBase(title: String): String =
        title.split(" ")
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() }}

    suspend fun formatTitleForApi(title: String): String =
        title.replace(" ", "+")
}