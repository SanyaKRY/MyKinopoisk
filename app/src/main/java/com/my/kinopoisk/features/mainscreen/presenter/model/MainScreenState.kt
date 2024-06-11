package com.my.kinopoisk.features.mainscreen.presenter.model

sealed interface MainScreenState {
    data object Initial : MainScreenState
    data object Loading : MainScreenState
    data object Error : MainScreenState
    data class Dataloaded(val items: List<FilmUi>) : MainScreenState
}