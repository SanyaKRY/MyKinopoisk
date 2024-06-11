package com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_film_table")
data class FavoriteFilmTable(
    @PrimaryKey(autoGenerate = true) var id : Int,
    val filmId: Int,
    val year: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: String,
    val countries: String
)
