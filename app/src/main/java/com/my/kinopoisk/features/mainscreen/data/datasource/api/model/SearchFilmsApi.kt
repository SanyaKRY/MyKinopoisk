package com.my.kinopoisk.features.mainscreen.data.datasource.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchFilmsApi(
    @Json(name = "films") val films: List<SearchFilmApi>
)

@JsonClass(generateAdapter = true)
data class SearchFilmApi(
    @Json(name = "filmId") val filmId: Int,
    @Json(name = "year") val year: String,
    @Json(name = "nameRu") val nameRu: String,
    @Json(name = "posterUrl") val posterUrl: String,
    @Json(name = "posterUrlPreview") val posterUrlPreview: String,
    @Json(name = "genres") val genres: List<Genre>,
    @Json(name = "countries") val countries: List<Country>
)
