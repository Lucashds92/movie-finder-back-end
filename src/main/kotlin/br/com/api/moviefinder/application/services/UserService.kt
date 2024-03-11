package br.com.api.moviefinder.application.services

import br.com.api.moviefinder.domain.model.User
import br.com.api.moviefinder.domain.model.UserRepresentation
import br.com.api.moviefinder.infrastructure.outgoing.repository.MongoAdapter
import org.springframework.stereotype.Service

@Service
class UserService(
    private val mongoAdapter: MongoAdapter
) {
    suspend fun getUser(userName: String): UserRepresentation =
        UserRepresentation(mongoAdapter.findByUserName(userName))

    suspend fun registerUser(user: User) {
        mongoAdapter.registerUser(user)
    }
}