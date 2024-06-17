package com.my.kinopoisk.features.favouritefilmscreen.data.repository

import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.FavoriteFilmDataBaseDataSource
import com.my.kinopoisk.features.favouritefilmscreen.data.repository.mapper.FavoriteFilmDataBaseToDomainMapper
import com.my.kinopoisk.features.favouritefilmscreen.domain.FavoriteFilmsScreenRepository
import com.my.kinopoisk.features.favouritefilmscreen.domain.model.FavoriteFilmDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteFilmsScreenRepositoryImpl @Inject constructor(
    private val favoriteFilmDataBaseDataSource: FavoriteFilmDataBaseDataSource
) : FavoriteFilmsScreenRepository {

    override fun getFavoriteFilms(): Flow<List<FavoriteFilmDomain>> {
        return favoriteFilmDataBaseDataSource.getAllFavoriteFilmFlow().map { result ->
            FavoriteFilmDataBaseToDomainMapper.map(result)
        }
    }

    override fun searchFavoriteFilm(searchQuery: String): Flow<List<FavoriteFilmDomain>> {
        return favoriteFilmDataBaseDataSource.searchFavoriteFilm(searchQuery).map { result ->
            FavoriteFilmDataBaseToDomainMapper.map(result)
        }
    }
}