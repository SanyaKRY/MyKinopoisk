package com.my.kinopoisk.features.favouritefilmscreen.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.dao.FavoriteFilmDao
import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.model.FavoriteFilmTable

@Database(entities = arrayOf(FavoriteFilmTable::class), version = 1, exportSchema = false)
abstract class FavoriteFilmDatabase : RoomDatabase() {

    abstract val favoriteFilmDao: FavoriteFilmDao
}