package com.my.kinopoisk.features.mainscreen.domain.usecase

import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import javax.inject.Inject

class SearchFilmUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    fun execute(searchQuery: String): List<FilmDomain> {
        return mainScreenRepository
    }
}