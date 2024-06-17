package com.my.kinopoisk.features.favouritefilmscreen.presenter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteScreenState
import com.my.kinopoisk.features.favouritefilmscreen.domain.usecase.GetFavoriteFilmsUseCase
import com.my.kinopoisk.features.favouritefilmscreen.domain.usecase.SearchFavoriteFilmUseCase
import com.my.kinopoisk.features.favouritefilmscreen.presenter.mapper.FavoriteFilmsDomainToUiMapper
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteFilmUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteFilmsViewModel @Inject constructor(
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
    private val searchFavoriteFilmUseCase: SearchFavoriteFilmUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<FavoriteScreenState> =
        MutableStateFlow(FavoriteScreenState.Initial)
    val stateFlow: Flow<FavoriteScreenState>
        get() = _stateFlow

    init {
        getListOfFilms()
    }

    fun searchFilm(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateFlow.value = FavoriteScreenState.Loading
            delay(1_000)
            val flow = searchFavoriteFilmUseCase.execute(searchQuery)
            withContext(Dispatchers.Main) {
                flow.collect {
                    val films: List<FavoriteFilmUi> = FavoriteFilmsDomainToUiMapper.map(it)
                    _stateFlow.value = FavoriteScreenState.DataLoaded(films)
                }
            }
        }
    }

    private fun getListOfFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1_000)
            val flow = getFavoriteFilmsUseCase.execute()
            withContext(Dispatchers.Main) {
                flow.collect {
                    val films: List<FavoriteFilmUi> = FavoriteFilmsDomainToUiMapper.map(it)
                    _stateFlow.value = FavoriteScreenState.DataLoaded(films)
                }
            }
        }
    }
}