package br.com.api.moviefinder.infrastructure.config

import br.com.api.moviefinder.application.interfaces.MovieInterface
import br.com.api.moviefinder.application.interfaces.OMDBApiInterface
import br.com.api.moviefinder.application.interfaces.UserInterface
import br.com.api.moviefinder.application.services.MovieService
import br.com.api.moviefinder.application.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ApplicationConfig {

    @Bean
    fun movieService(
        movieInterface: MovieInterface,
        omdbApiInterface: OMDBApiInterface
    ): MovieService =
        MovieService(movieInterface, omdbApiInterface)

    @Bean
    fun userService(
        userInterface: UserInterface
    ): UserService =
        UserService(userInterface)
}