package com.my.kinopoisk.features.mainscreen.presenter.model

data class FilmUi(
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: List<String>,
    val countries: List<String>,
    var isSavedToDataBase: Boolean
)
