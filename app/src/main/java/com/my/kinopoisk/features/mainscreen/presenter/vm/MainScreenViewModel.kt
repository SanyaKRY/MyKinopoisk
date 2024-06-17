package com.my.kinopoisk.features.mainscreen.presenter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.kinopoisk.features.mainscreen.domain.usecase.AddToFilmFavoriteUseCase
import com.my.kinopoisk.features.mainscreen.domain.usecase.GetListOfFilmsUseCase
import com.my.kinopoisk.features.mainscreen.domain.usecase.RemoveFromFilmFavoriteUseCase
import com.my.kinopoisk.features.mainscreen.domain.usecase.SearchFilmUseCase
import com.my.kinopoisk.features.mainscreen.presenter.mapper.FilmDomainToUiMapper
import com.my.kinopoisk.features.mainscreen.presenter.mapper.FilmUiToDomainMapper
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi
import com.my.kinopoisk.features.mainscreen.presenter.model.MainScreenState
import com.my.kinopoisk.util.extensions.runCatchingNonCancellation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getListOfFilmsUseCase: GetListOfFilmsUseCase,
    private val addToFilmFavoriteUseCase: AddToFilmFavoriteUseCase,
    private val removeFromFilmFavoriteUseCase: RemoveFromFilmFavoriteUseCase,
    private val searchFilmUseCase: SearchFilmUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val stateFlow: Flow<MainScreenState>
        get() = _stateFlow

    init {
        getListOfFilms()
    }

    fun addToFavoriteFilm(film: FilmUi) {
        viewModelScope.launch(Dispatchers.IO) {
            val addToFavoriteFilm = addToFilmFavoriteUseCase.execute(FilmUiToDomainMapper.map(film))
            withContext(Dispatchers.Main) {
            }
        }
    }

    fun removeFromFavoriteFilm(film: FilmUi) {
        viewModelScope.launch(Dispatchers.IO) {
            val removeFromFavoriteFilm = removeFromFilmFavoriteUseCase.execute(FilmUiToDomainMapper.map(film))
            withContext(Dispatchers.Main) {
            }
        }
    }


    fun searchFilm(searchQuery: String) {

    }

    private fun getListOfFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2_000)
            val screenState: MainScreenState = runCatchingNonCancellation {
                getListOfFilmsUseCase.execute()
            }.fold(
                onSuccess = {
                    MainScreenState.Dataloaded(FilmDomainToUiMapper.map(it))
                },
                onFailure = {
                    MainScreenState.Error
                }
            )
            withContext(Dispatchers.Main) {
                _stateFlow.value = screenState
            }
        }
    }
}