package com.my.kinopoisk.features.mainscreen.data.repository

import com.my.kinopoisk.features.mainscreen.data.datasource.api.FilmsNetworkDataSource
import com.my.kinopoisk.features.mainscreen.data.repository.mapper.FilmApiToDomainMapper
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val filmsNetworkDataSource: FilmsNetworkDataSource
) : MainScreenRepository {

    override suspend fun getListOfFilms(page: Int): List<FilmDomain> {
        return FilmApiToDomainMapper.map(filmsNetworkDataSource.getListOfFilms(page).films)
    }
}