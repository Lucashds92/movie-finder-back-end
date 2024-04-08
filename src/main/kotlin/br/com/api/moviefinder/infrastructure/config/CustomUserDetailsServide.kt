package br.com.api.moviefinder.infrastructure.config

import br.com.api.moviefinder.infrastructure.outgoing.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = br.com.api.moviefinder.infrastructure.incoming.model.UserEntity

@Service
class CostumUserDetailsServide(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        runBlocking {
            userRepository.findByUserName(username)
                .awaitSingleOrNull()
                ?.mapToUserDetails()
                ?: throw UsernameNotFoundException("CostumUserDetailsServide::loadUserByUsername: User not found with username: $username")
        }

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.userName)
            .password(this.password)
            .roles(this.role.name)
            .build()

}