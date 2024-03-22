package br.com.api.moviefinder.infrastructure.outgoing.repository

import br.com.api.moviefinder.infrastructure.incoming.model.UserEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: ReactiveMongoRepository<UserEntity, String> {

    fun findByUserName(username: String): Mono<UserEntity>

    fun save(userEntity: UserEntity): Mono<UserEntity>
}