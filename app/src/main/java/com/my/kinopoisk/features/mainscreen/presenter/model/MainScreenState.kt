package com.my.kinopoisk.features.mainscreen.presenter.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed interface MainScreenState {
    data object Initial : MainScreenState
    data object Loading : MainScreenState
    data object Error : MainScreenState
    data class Dataloaded(val items: PagingData<FilmUi>) : MainScreenState
}