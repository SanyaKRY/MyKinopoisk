package com.my.kinopoisk.features.mainscreen.presenter.mapper

import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi

object FilmDomainToUiMapper {
    fun map(type: List<FilmDomain>): List<FilmUi> {
        return type.map {
            FilmUi(
                filmId = it.filmId,
                year = it.year,
                nameRu = it.nameRu,
                posterUrl = it.posterUrlPreview,
                posterUrlPreview = it.posterUrlPreview,
                genres = it.genres,
                countries = it.countries,
                isSavedToDataBase = it.isSavedToDataBase
            )
        }
    }
}