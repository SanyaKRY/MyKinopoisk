package com.my.kinopoisk.features.favouritefilmscreen.domain

import com.my.kinopoisk.features.favouritefilmscreen.domain.model.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow

interface FavoriteFilmsScreenRepository {

    fun getFavoriteFilms(): Flow<List<FavoriteFilmDomain>>
}