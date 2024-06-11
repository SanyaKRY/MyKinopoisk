package com.my.kinopoisk.features.mainscreen.domain.usecase

import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import javax.inject.Inject

class AddToFilmFavoriteUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(film: FilmDomain): Any {
        return mainScreenRepository.addToFavoriteFilm(film)
    }
}