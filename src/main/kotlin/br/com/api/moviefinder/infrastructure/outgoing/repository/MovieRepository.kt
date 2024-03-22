package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.infrastructure.incoming.model.MovieEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface MovieRepository : ReactiveMongoRepository<MovieEntity, String> {

    override fun findAll(): Flux<MovieEntity>

    fun findByImdbID(imdbID: String): Mono<MovieEntity>

    fun save(movieEntity: MovieEntity): Mono<MovieEntity>

    fun findByTitle(title: String): Mono<MovieEntity>
}