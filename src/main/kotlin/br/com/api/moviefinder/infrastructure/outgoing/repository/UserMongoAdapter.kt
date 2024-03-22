package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.application.exceptions.NotFoundException
import br.com.api.moviefinder.application.interfaces.UserInterface
import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.MovieRepresentation
import br.com.api.moviefinder.domain.model.User
import br.com.api.moviefinder.infrastructure.incoming.model.MovieEntity
import br.com.api.moviefinder.infrastructure.incoming.model.UserEntity
import br.com.api.moviefinder.infrastructure.outgoing.mappers.Mappers
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component

@Component
class UserMongoAdapter(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val mappers: Mappers
): UserInterface {

    override suspend fun findByUserName(userName: String): User {
        val userEntity = userRepository.findByUserName(userName)
            .awaitSingleOrNull() ?: throw NotFoundException("UserMongoAdapter::findByUsername: User not found with username: $userName")
        return mappers.mapUserEntityToUser(userEntity)
    }

    override suspend fun registerUser(user: User) {
        userRepository.save(UserEntity(user))
            .awaitSingleOrNull() ?: throw NotFoundException("UserMongoAdapter::registerUser: Could not register user with username: ${user.userName}")
    }

    override suspend fun addFavoriteMovie(username: String, movie: Movie): Movie {
        findByUserName(username)
            .let { user ->
                user.favoriteMovies?.find { it.imdbId == movie.imdbID }
                    ?: user.let {
                        val updatedUser = it.copy(
                            favoriteMovies = it.favoriteMovies
                                ?.plus(MovieRepresentation(movie)))
                        userRepository.save(UserEntity(updatedUser)).awaitSingleOrNull()
                            ?: throw Exception("UserMongoAdapter::addFavoriteMovie: Could not add movie (imdbId: ${movie.imdbID}) to user (${username}) favorite movies.")
                    }
            }
        return mappers.mapMovieEntityToMovie(
            saveMovie(movie)
        )
    }

    suspend fun saveMovie(movie: Movie): MovieEntity =
        movie.imdbID?.let { movieRepository.findByImdbID(it).awaitSingleOrNull() }
            ?: movieRepository.save(mappers.mapMovieToMovieEntity(movie)).awaitSingleOrNull()
        ?: throw Exception("UserMongoAdapter::saveMovie: Could not save movie (imdbId: ${movie.imdbID}).")

    override suspend fun deleteByImdbID(imdbID: String, username: String) {
        val user = findByUserName(username)

        user.favoriteMovies?.find {
            it.imdbId == imdbID
        }?.let {
            val updatedFavoriteMovies = user.favoriteMovies.minus(it)

            val updatedUser = user.copy(favoriteMovies = updatedFavoriteMovies)

            userRepository.save(UserEntity(updatedUser)).awaitSingleOrNull()
                ?: throw Exception("UserMongoAdapter::deleteByImdbID: Could not delete movie (imdbId: $imdbID) from user (${username}) list of favorite movies.")
        } ?: throw Exception("UserMongoAdapter::deleteByImdbID: User ($username), does not have a movie with the provided imdbId ($imdbID).")
    }
}