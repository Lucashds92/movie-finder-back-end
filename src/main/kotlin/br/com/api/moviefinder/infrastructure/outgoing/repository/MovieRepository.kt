package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.domain.model.Movie
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MovieRepository: ReactiveMongoRepository<Movie, String> {

    override fun findAll(): Flux<Movie>

    fun findByImdbID(imdbID: String): Mono<Movie>

    fun save(movie: Movie): Mono<Movie>

    fun deleteByImdbID(imdbID: String): Mono<Movie>
}