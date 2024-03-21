package br.com.api.moviefinder.infrastructure.outgoing.mappers

import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.User
import br.com.api.moviefinder.infrastructure.incoming.model.MovieEntity
import br.com.api.moviefinder.infrastructure.incoming.model.UserEntity
import org.springframework.stereotype.Component

@Component
class Mappers {

    fun mapMovieToMovieEntity(movie: Movie): MovieEntity =
        MovieEntity(
            title = movie.title,
            year = movie.year,
            released = movie.released,
            runtime = movie.runtime,
            genre = movie.genre,
            director = movie.director,
            writer = movie.writer,
            actors = movie.actors,
            plot = movie.plot,
            language = movie.language,
            country = movie.country,
            awards = movie.awards,
            poster = movie.poster,
            imdbRating = movie.imdbRating,
            imdbVotes = movie.imdbVotes,
            imdbID = movie.imdbID,
            type = movie.type,
            boxOffice = movie.boxOffice
        )

    fun mapMovieEntityToMovie(movieEntity: MovieEntity): Movie =
        Movie(
            title = movieEntity.title,
            year = movieEntity.year,
            released = movieEntity.released,
            runtime = movieEntity.runtime,
            genre = movieEntity.genre,
            director = movieEntity.director,
            writer = movieEntity.writer,
            actors = movieEntity.actors,
            plot = movieEntity.plot,
            language = movieEntity.language,
            country = movieEntity.country,
            awards = movieEntity.awards,
            poster = movieEntity.poster,
            imdbRating = movieEntity.imdbRating,
            imdbVotes = movieEntity.imdbVotes,
            imdbID = movieEntity.imdbID,
            type = movieEntity.type,
            boxOffice = movieEntity.boxOffice
        )

    fun mapUserEntityToUser(userEntity: UserEntity): User =
        User(
            id = userEntity.id,
            userName = userEntity.userName,
            password = userEntity.password,
            favoriteMovies = userEntity.favoriteMovies
        )
}