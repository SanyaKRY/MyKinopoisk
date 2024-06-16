package com.my.kinopoisk.di

import com.my.kinopoisk.features.favouritefilmscreen.data.repository.FavoriteFilmsScreenRepositoryImpl
import com.my.kinopoisk.features.favouritefilmscreen.domain.FavoriteFilmsScreenRepository
import com.my.kinopoisk.features.mainscreen.data.repository.MainScreenRepositoryImpl
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainScreenRepository(
        mainScreenRepositoryImpl: MainScreenRepositoryImpl
    ): MainScreenRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteFilmsScreenRepository(
        favoriteFilmsScreenRepositoryImpl: FavoriteFilmsScreenRepositoryImpl
    ): FavoriteFilmsScreenRepository
}