package com.my.kinopoisk.features.favouritefilmscreen.presenter.mapper

import com.my.kinopoisk.features.favouritefilmscreen.domain.model.FavoriteFilmDomain
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteFilmUi

object FavoriteFilmsDomainToUiMapper {
    fun map(type: List<FavoriteFilmDomain>): List<FavoriteFilmUi> {
        return type.map {
            FavoriteFilmUi(
                filmId = it.filmId,
                year = it.year,
                nameRu = it.nameRu,
                posterUrl = it.posterUrl,
                posterUrlPreview = it.posterUrlPreview,
                genres = it.genres,
                countries = it.countries
            )
        }
    }
}