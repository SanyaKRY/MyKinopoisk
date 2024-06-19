package com.my.kinopoisk.features.mainscreen.domain.repository

import androidx.paging.PagingData
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    suspend fun getListOfFilms(): Flow<PagingData<FilmDomain>>

    suspend fun addToFavoriteFilm(film: FilmDomain): Any

    suspend fun removeFromFavoriteFilm(film: FilmDomain): Any

    suspend fun searchFilm(searchQuery: String): List<FilmDomain>
}