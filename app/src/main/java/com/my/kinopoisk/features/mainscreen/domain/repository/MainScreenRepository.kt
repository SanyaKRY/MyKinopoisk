package com.my.kinopoisk.features.mainscreen.domain.repository

import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain

interface MainScreenRepository {

    suspend fun getListOfFilms(page: Int): List<FilmDomain>

    suspend fun addToFavoriteFilm(film: FilmDomain): Any

    suspend fun removeFromFavoriteFilm(film: FilmDomain): Any

    suspend fun searchFilm(searchQuery: String): List<FilmDomain>
}