package com.my.kinopoisk.features.favouritefilmscreen.data.repository.mapper

import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.model.FavoriteFilmTable
import com.my.kinopoisk.features.favouritefilmscreen.domain.model.FavoriteFilmDomain
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain

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
                countries = it.countries
            )
        }
    }
}

object FilmDomainToDataBaseMapper {
    fun map(type: FilmDomain): FavoriteFilmTable {
        return FavoriteFilmTable(
            id = 0,
            filmId = type.filmId,
            year = type.year,
            nameRu = type.nameRu,
            posterUrl = type.posterUrl,
            posterUrlPreview = type.posterUrlPreview,
            genres = type.genres.joinToString(separator = ", "),
            countries = type.countries.joinToString(separator = ", ")
        )
    }
}