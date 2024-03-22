package br.com.api.moviefinder.application.services

import br.com.api.moviefinder.application.interfaces.UserInterface
import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.User
import br.com.api.moviefinder.domain.model.UserRepresentation

class UserService(
    private val userInterface: UserInterface
) {
    suspend fun getUser(userName: String): UserRepresentation =
        UserRepresentation(userInterface.findByUserName(userName))

    suspend fun registerUser(user: User) {
        userInterface.registerUser(user)
    }

    suspend fun favoriteMovie(username: String, movie: Movie) {
        userInterface.addFavoriteMovie(username, movie)
    }

    suspend fun deleteMovie(imdbID: String, username: String) =
        userInterface.deleteByImdbID(imdbID, username)
}