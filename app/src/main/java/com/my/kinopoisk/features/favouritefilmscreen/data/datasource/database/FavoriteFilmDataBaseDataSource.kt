package com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database

import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.dao.FavoriteFilmDao
import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.model.FavoriteFilmTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteFilmDataBaseDataSource @Inject constructor(
    private val favoriteFilmDao: FavoriteFilmDao
) {

    suspend fun insertFavoriteFilm(film: FavoriteFilmTable): Any {
        return favoriteFilmDao.insertFavoriteFilm(film)
    }

    fun getAllFavoriteFilmFlow(): Flow<List<FavoriteFilmTable>> {
        return favoriteFilmDao.getAllFavoriteFilmFlow()
    }
}