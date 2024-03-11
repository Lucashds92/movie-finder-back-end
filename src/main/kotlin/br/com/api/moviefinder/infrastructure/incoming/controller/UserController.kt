package br.com.api.moviefinder.infrastructure.incoming.controller

import br.com.api.moviefinder.application.services.UserService
import br.com.api.moviefinder.domain.model.User
import br.com.api.moviefinder.domain.model.UserRepresentation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val service: UserService
) {

    @GetMapping("/{userName}")
    suspend fun getUser(@PathVariable userName: String): UserRepresentation =
        service.getUser(userName)

    @PostMapping("/register")
    suspend fun registerUser(@RequestBody user: User) =
        service.registerUser(user)
}