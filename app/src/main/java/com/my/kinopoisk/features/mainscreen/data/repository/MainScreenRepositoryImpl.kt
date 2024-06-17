package com.my.kinopoisk.features.mainscreen.data.repository

import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.FavoriteFilmDataBaseDataSource
import com.my.kinopoisk.features.favouritefilmscreen.data.repository.mapper.FilmDomainToDataBaseMapper
import com.my.kinopoisk.features.mainscreen.data.datasource.api.FilmsNetworkDataSource
import com.my.kinopoisk.features.mainscreen.data.repository.mapper.FilmApiToDomainMapper
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val filmsNetworkDataSource: FilmsNetworkDataSource,
    private val favoriteFilmDataBaseDataSource: FavoriteFilmDataBaseDataSource
) : MainScreenRepository {

    override suspend fun getListOfFilms(page: Int): List<FilmDomain> {
        return FilmApiToDomainMapper.map(filmsNetworkDataSource.getListOfFilms(page).films)
    }

    override suspend fun addToFavoriteFilm(film: FilmDomain): Any {
        return favoriteFilmDataBaseDataSource.insertFavoriteFilm(FilmDomainToDataBaseMapper.map(film))
    }

    override suspend fun removeFromFavoriteFilm(film: FilmDomain): Any {
        return favoriteFilmDataBaseDataSource.deleteFavoriteFilm(FilmDomainToDataBaseMapper.map(film))
    }

    override suspend fun searchFilm(searchQuery: String): List<FilmDomain> {
        return FilmApiToDomainMapper.map(filmsNetworkDataSource.searchFilmByKey(searchQuery).films)
    }
}