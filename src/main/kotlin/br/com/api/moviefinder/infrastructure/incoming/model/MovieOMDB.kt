package br.com.api.moviefinder.infrastructure.incoming.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieOMDB(
    @JsonProperty("Title")
    val title: String? = null,

    @JsonProperty("Year")
    val year: String? = null,

    @JsonProperty("Released")
    val released: String? = null,

    @JsonProperty("Runtime")
    val runtime: String? = null,

    @JsonProperty("Genre")
    val genre: String? = null,

    @JsonProperty("Director")
    val director: String? = null,

    @JsonProperty("Writer")
    val writer: String? = null,

    @JsonProperty("Actors")
    val actors: String? = null,

    @JsonProperty("Plot")
    val plot: String? = null,

    @JsonProperty("Language")
    val language: String? = null,

    @JsonProperty("Country")
    val country: String? = null,

    @JsonProperty("Awards")
    val awards: String? = null,

    @JsonProperty("Poster")
    val poster: String? = null,

    val imdbRating: String? = null,
    val imdbVotes: String? = null,
    val imdbID: String? = null,

    @JsonProperty("Type")
    val type: String? = null,

    @JsonProperty("BoxOffice")
    val boxOffice: String? = null
)
