package com.my.kinopoisk.features.favouritefilmscreen.presenter.model

data class FavoriteFilmUi(
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: String,
    val countries: String
)
