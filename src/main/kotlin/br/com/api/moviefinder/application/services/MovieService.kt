package br.com.api.moviefinder.application.services

import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.MovieRepresentation
import br.com.api.moviefinder.infrastructure.outgoing.repository.Mappers
import br.com.api.moviefinder.infrastructure.outgoing.OMDBApi.OMDBApiAdapter
import br.com.api.moviefinder.infrastructure.outgoing.repository.MongoAdapter
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val api: OMDBApiAdapter,
    private val mongoAdapter: MongoAdapter,
    private val mappers: Mappers
) {

    suspend fun getMovies(): List<MovieRepresentation> {
        var movies: List<MovieRepresentation> = ArrayList()
        mongoAdapter.findAll()
            .forEach {
                movies = movies.plus(MovieRepresentation(it))
            }
        return movies
    }

    suspend fun getMovieById(imdbID: String): Movie =
        mongoAdapter.findByImdbID(imdbID)

    suspend fun findMovie(title: String): Movie {
        val correctTitle = correctTitle(title)
        return api.findMovie(correctTitle)
    }

    suspend fun correctTitle(title: String): String{
        return title.replace(" ", "+")
    }

    suspend fun favoriteMovie(movie: Movie): Movie? =
        movie.imdbID?.let { mongoAdapter.findByImdbID(it) }
            ?: mongoAdapter.save(
                mappers.mapApiMovieToMovieEntity(movie)
            )

    suspend fun deleteMovie(imdbID: String) {
        mongoAdapter.deleteByImdbID(imdbID)
    }
}