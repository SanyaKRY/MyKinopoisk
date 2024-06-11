package com.my.kinopoisk.features.favouritefilmscreen.presenter.model

sealed interface FavoriteScreenState {
    data object Initial : FavoriteScreenState
    data object Loading : FavoriteScreenState
    data object Error : FavoriteScreenState
    data class DataLoaded(val items: List<FavoriteFilmUi>) : FavoriteScreenState
}