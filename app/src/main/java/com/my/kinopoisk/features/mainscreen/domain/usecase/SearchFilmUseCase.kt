package com.my.kinopoisk.features.mainscreen.domain.usecase

import android.util.Log
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import javax.inject.Inject

class SearchFilmUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(searchQuery: String): List<FilmDomain> {
        Log.d("dfsADFSADFASD","dddd: $searchQuery ")
        val result = mainScreenRepository.searchFilm(searchQuery)
        Log.d("dfsADFSADFASD","dddd: $result")
        return result
    }
}