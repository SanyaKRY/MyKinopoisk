package com.my.kinopoisk.features.mainscreen.data.datasource.api

import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.FilmsApi
import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.SearchFilmsApi
import com.my.kinopoisk.features.mainscreen.data.datasource.api.retrofit.FilmsApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class FilmsNetworkDataSource @Inject constructor(
    private val filmsApiService: FilmsApiService
) {

    suspend fun getListOfFilms(page: Int): FilmsApi {
//        delay(20_000)
        return filmsApiService.getListOfFilms(page)
    }

    suspend fun searchFilmByKey(keyword: String): SearchFilmsApi {
        return filmsApiService.searchFilmByKey(keyword)
    }
}