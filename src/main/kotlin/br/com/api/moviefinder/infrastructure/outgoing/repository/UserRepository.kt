package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.domain.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository: ReactiveMongoRepository<User, String> {

    fun findByUserName(username: String): Mono<User>

    fun save(user: User): Mono<User>
}