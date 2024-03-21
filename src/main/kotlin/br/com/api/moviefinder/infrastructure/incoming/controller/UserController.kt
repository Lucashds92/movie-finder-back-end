package br.com.api.moviefinder.infrastructure.incoming.controller

import br.com.api.moviefinder.application.services.UserService
import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.User
import br.com.api.moviefinder.domain.model.UserRepresentation
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController(
    @Qualifier("userService")
    private val userService: UserService
) {

    @GetMapping("/{userName}")
    suspend fun getUser(@PathVariable userName: String): UserRepresentation =
        userService.getUser(userName)

    @PostMapping("/register")
    suspend fun registerUser(@RequestBody user: User) =
        userService.registerUser(user)

    @PostMapping("/favorite/{username}")
    suspend fun favoriteMovie(
        @PathVariable username: String,
        @RequestBody movie: Movie
    ) {
        userService.favoriteMovie(username, movie)
    }

    @DeleteMapping("delete/{imdbID}/{username}")
    suspend fun deleteMovie(
        @PathVariable imdbID: String,
        @PathVariable username: String
    ) {
        userService.deleteMovie(imdbID, username)
    }
}