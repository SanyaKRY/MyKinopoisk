package com.my.kinopoisk.features.favouritefilmscreen.domain.usecase

import com.my.kinopoisk.features.favouritefilmscreen.domain.FavoriteFilmsScreenRepository
import com.my.kinopoisk.features.favouritefilmscreen.domain.model.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(
    private val favoriteFilmsScreenRepository: FavoriteFilmsScreenRepository
) {

    fun execute(): Flow<List<FavoriteFilmDomain>> {
        return favoriteFilmsScreenRepository.getFavoriteFilms()
    }
}