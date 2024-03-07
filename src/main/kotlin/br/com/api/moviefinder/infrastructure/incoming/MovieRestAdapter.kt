package br.com.api.moviefinder.infrastructure.incoming

import br.com.api.moviefinder.application.services.MovieService
import br.com.api.moviefinder.domain.model.Movie
import br.com.api.moviefinder.domain.model.MovieRepresentation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/movies")
class MovieRestAdapter(
    private val service: MovieService
) {

    @GetMapping
    suspend fun getMovies(): List<MovieRepresentation> {
        return service.getMovies()
    }

    @GetMapping("/id/{imdbID}")
    suspend fun getMovieById(@PathVariable imdbID: String): Movie {
        return service.getMovieById(imdbID)
    }

    @GetMapping("/title/{title}")
    suspend fun findMovie(@PathVariable title: String): Movie {
        return service.findMovie(title)
    }

    @PostMapping
    suspend fun favoriteMovie(@RequestBody movie: Movie) {
        service.favoriteMovie(movie)
    }

    @DeleteMapping("delete/{imdbID}")
    suspend fun deleteMovie(@PathVariable imdbID: String) {
        service.deleteMovie(imdbID)
    }

}