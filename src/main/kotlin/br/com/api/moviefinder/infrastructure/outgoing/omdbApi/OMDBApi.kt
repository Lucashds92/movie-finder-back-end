package br.com.api.moviefinder.infrastructure.outgoing.omdbApi

import br.com.api.moviefinder.infrastructure.incoming.model.MovieEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class OMDBApi {

    private val apikey = "954ab431"
    private val webClient = WebClient.builder()
        .baseUrl("https://www.omdbapi.com")
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build()

    suspend fun findMovie(title: String): MovieEntity {
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