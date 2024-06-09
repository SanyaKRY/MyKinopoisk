package com.my.kinopoisk.features.mainscreen.data.datasource.api

import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.FilmsApi
import com.my.kinopoisk.features.mainscreen.data.datasource.api.retrofit.FilmsApiService
import javax.inject.Inject

class FilmsNetworkDataSource @Inject constructor(
    private val filmsApiService: FilmsApiService
) {

    suspend fun getListOfFilms(page: Int): FilmsApi {
        return filmsApiService.getListOfFilms(page)
    }

    suspend fun searchFilmByKey(keyword: String): FilmsApi {
        return filmsApiService.searchFilmByKey(keyword)
    }
}