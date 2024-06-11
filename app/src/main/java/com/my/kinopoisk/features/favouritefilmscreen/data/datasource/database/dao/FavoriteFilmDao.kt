package com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.model.FavoriteFilmTable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFilmDao {

    @Insert
    suspend fun insertFavoriteFilm(film: FavoriteFilmTable): Long

    @Query("SELECT * FROM favorite_film_table")
    fun getAllFavoriteFilmFlow(): Flow<List<FavoriteFilmTable>>
}