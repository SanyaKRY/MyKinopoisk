package com.my.kinopoisk.di

import android.content.Context
import androidx.room.Room
import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.FavoriteFilmDatabase
import com.my.kinopoisk.features.favouritefilmscreen.data.datasource.database.dao.FavoriteFilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavoriteFilmDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteFilmDatabase::class.java,
            "favorite_film_databas"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteFilmDao(dataBase: FavoriteFilmDatabase): FavoriteFilmDao =
        dataBase.favoriteFilmDao
}