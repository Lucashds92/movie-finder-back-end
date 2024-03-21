package br.com.api.moviefinder.application.interfaces

import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.User

interface UserInterface {
    suspend fun findByUserName(userName: String): User

    suspend fun registerUser(user: User)

    suspend fun addFavoriteMovie(username: String, movie: Movie): Movie

    suspend fun deleteByImdbID(imdbID: String, username: String)
}