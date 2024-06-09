package com.my.kinopoisk.features.mainscreen.domain.repository

import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain

interface MainScreenRepository {

    suspend fun getListOfFilms(page: Int): List<FilmDomain>
}