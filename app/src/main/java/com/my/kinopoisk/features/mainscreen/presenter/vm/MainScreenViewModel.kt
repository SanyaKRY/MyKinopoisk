package com.my.kinopoisk.features.mainscreen.presenter.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.log
import androidx.paging.map
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import com.my.kinopoisk.features.mainscreen.domain.usecase.AddToFilmFavoriteUseCase
import com.my.kinopoisk.features.mainscreen.domain.usecase.GetListOfFilmsUseCase
import com.my.kinopoisk.features.mainscreen.domain.usecase.RemoveFromFilmFavoriteUseCase
import com.my.kinopoisk.features.mainscreen.domain.usecase.SearchFilmUseCase
import com.my.kinopoisk.features.mainscreen.presenter.mapper.FilmDomainToUiMapper
import com.my.kinopoisk.features.mainscreen.presenter.mapper.FilmsDomainToUiMapper
import com.my.kinopoisk.features.mainscreen.presenter.mapper.FilmUiToDomainMapper
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi
import com.my.kinopoisk.features.mainscreen.presenter.model.MainScreenState
import com.my.kinopoisk.util.extensions.runCatchingNonCancellation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
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
    val searchQueryPublisher = MutableSharedFlow<String>(extraBufferCapacity = 1)

//    private val _pagingFlow: MutableStateFlow<PagingData<FilmUi>> =
//        MutableStateFlow(PagingData.empty())
//    val pagingFlow: Flow<PagingData<FilmUi>>
//        get() = _pagingFlow

    init {
        getListOfFilms()
        listenToSearchQuery()
    }

//    fun onSearchChanged(searchQuery: String) {
//        if (searchQuery.isNotEmpty()) {
//            searchFilm(searchQuery)
//        } else {
//            getListOfFilms()
//        }
//    }

    fun addToFavoriteFilm(film: FilmUi) {
        viewModelScope.launch(Dispatchers.IO) {
            val addToFavoriteFilm = addToFilmFavoriteUseCase.execute(FilmUiToDomainMapper.map(film))
            withContext(Dispatchers.Main) {
            }
        }
    }

    fun removeFromFavoriteFilm(film: FilmUi) {
        viewModelScope.launch(Dispatchers.IO) {
            val removeFromFavoriteFilm =
                removeFromFilmFavoriteUseCase.execute(FilmUiToDomainMapper.map(film))
            withContext(Dispatchers.Main) {
            }
        }
    }


    private fun listenToSearchQuery() {
        searchQueryPublisher
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .debounce(500)
            .mapLatest {
                _stateFlow.value = MainScreenState.Loading
                val s = searchFilm(it)
                Log.d("sdfsagdsagdsfsdsfgsf","DFG: $s")
                s
            }
            .flowOn(Dispatchers.Default)
            .onEach {
                Log.d("sdfsagdsagddsfsdsfgsf","DFG: $it")
                _stateFlow.value = it
            }
            .launchIn(viewModelScope)
    }

    private suspend fun searchFilm(searchQuery: String): MainScreenState {
        val a = runCatchingNonCancellation {
            searchFilmUseCase.execute(searchQuery)
        }.map {
            MainScreenState.Dataloaded(PagingData.from(FilmsDomainToUiMapper.map(it)))
        }.getOrDefault(MainScreenState.Error)
        Log.d("dzfsdfsdfs","DD: $a")
        return a
//        viewModelScope.launch(Dispatchers.IO) {
//            _stateFlow.value = MainScreenState.Loading
//            delay(1_000)
//            val screenState: MainScreenState = runCatchingNonCancellation {
//                searchFilmUseCase.execute(searchQuery)
//            }.fold(
//                onSuccess = {
//                    MainScreenState.Dataloaded(FilmsDomainToUiMapper.map(it))
//                },
//                onFailure = {
//                    MainScreenState.Error
//                }
//            )
//            withContext(Dispatchers.Main) {
//                _stateFlow.value = screenState
//            }
//        }
    }

    private fun getListOfFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            val flow: Flow<PagingData<FilmUi>> = getListOfFilmsUseCase.execute().map { pagingData ->
                pagingData.map {
                    FilmDomainToUiMapper.map(it)
                }
            }.cachedIn(viewModelScope)
            flow.collect {
                _stateFlow.value = MainScreenState.Dataloaded(it)
            }
//                .onEach { pagingData ->
//                    _stateFlow.value = MainScreenState.Dataloaded(pagingData.map {
//                        FilmDomainToUiMapper.map(it)
//                    })
//                }.catch {
//                    _stateFlow.value = MainScreenState.Error
//                }
//                .collect()
//                .cachedIn(viewModelScope)

//            val flow: Flow<PagingData<FilmUi>> = getListOfFilmsUseCase
//                .execute()
//                .map { pagingData ->
//                    pagingData.map {
//                        FilmDomainToUiMapper.map(it)
//                    }
//                }
//                .cachedIn(viewModelScope)
//            flow.collect {
//                _pagingFlow.value = it
//            }

//            withContext(Dispatchers.Main) {
//                flow.map {
//                    _pagingFlow.value = it
//                }
//            }
//            delay(2_000)
//            val screenState: MainScreenState = runCatchingNonCancellation {
//                getListOfFilmsUseCase.execute()
//            }.fold(
//                onSuccess = {
//                    val flow = it.map {
//                        val filmUi: PagingData<FilmUi> = it.map {
//                            FilmDomainToUiMapper.map(it)
//                        }
//                        filmUi
//                    }
//                    MainScreenState.Dataloaded(flow)
//                },
//                onFailure = {
//                    MainScreenState.Error
//                }
//            )
//            withContext(Dispatchers.Main) {
//                _stateFlow.value = screenState
//            }
        }
    }
}