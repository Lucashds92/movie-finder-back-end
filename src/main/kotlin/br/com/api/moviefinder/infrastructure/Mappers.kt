package br.com.api.moviefinder.infrastructure

import br.com.api.moviefinder.domain.model.Movie
import org.springframework.stereotype.Component

@Component
class Mappers {

    fun mapApiMovieToMovieEntity(movie: Movie): Movie =
        movie.let {
            Movie(
                id = it.id,
                title = it.title,
                year = it.year,
                released = it.released,
                runtime = it.runtime,
                genre = it.genre,
                director = it.director,
                writer = it.writer,
                actors = it.actors,
                plot = it.plot,
                language = it.language,
                country = it.country,
                awards = it.awards,
                poster = it.poster,
                imdbRating = it.imdbRating,
                imdbVotes = it.imdbVotes,
                imdbID = it.imdbID,
                type = it.type,
                boxOffice = it.boxOffice
            )
        }
}