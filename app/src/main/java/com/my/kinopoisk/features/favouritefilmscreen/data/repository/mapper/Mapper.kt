package com.my.kinopoisk.features.favouritefilmscreen.data.repository.mapper

import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.model.FavoriteFilmTable
import com.my.kinopoisk.features.favouritefilmscreen.domain.model.FavoriteFilmDomain

object FavoriteFilmDataBaseToDomainMapper {
    fun map(type: List<FavoriteFilmTable>): List<FavoriteFilmDomain> {
        return type.map {
            FavoriteFilmDomain(
                filmId = it.filmId,
                year = it.year,
                nameRu = it.nameRu,
                posterUrl = it.posterUrl,
                posterUrlPreview = it.posterUrlPreview,
                genres = it.genres,
                countries= it.countries
            )
        }
    }
}