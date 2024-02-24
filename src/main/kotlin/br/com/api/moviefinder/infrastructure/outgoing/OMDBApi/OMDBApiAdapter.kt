package br.com.api.moviefinder.infrastructure.outgoing.OMDBApi

import br.com.api.moviefinder.domain.model.Movie
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class OMDBApiAdapter {

    private val apikey = "954ab431"
    private val webClient = WebClient.builder()
        .baseUrl("https://www.omdbapi.com")
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build()

    suspend fun findMovie(title: String): Movie {
        try {
            return webClient
                .get()
                .uri("/?t=$title&apikey=$apikey")
                .retrieve()
                .awaitBody()
        } catch (ex: Exception) {
            throw ex
        }
    }

}