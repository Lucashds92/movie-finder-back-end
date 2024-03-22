package br.com.api.moviefinder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieFinderApplication

fun main(args: Array<String>) {
    runApplication<MovieFinderApplication>(*args)
}
