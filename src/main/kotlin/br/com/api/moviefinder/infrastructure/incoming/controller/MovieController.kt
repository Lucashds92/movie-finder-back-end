package br.com.api.moviefinder.infrastructure.incoming.controller

import br.com.api.moviefinder.application.services.MovieService
import br.com.api.moviefinder.domain.model.Movie
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/movies")
class MovieController(
    @Qualifier("movieService")
    private val movieService: MovieService
) {

    @GetMapping("/id/{imdbID}")
    suspend fun getMovieById(@PathVariable imdbID: String): Movie {
        return movieService.getMovieById(imdbID)
    }

    @GetMapping("/title/{title}")
    suspend fun findMovie(
        @PathVariable title: String
    ): Movie =
        movieService.findMovie(title)
}