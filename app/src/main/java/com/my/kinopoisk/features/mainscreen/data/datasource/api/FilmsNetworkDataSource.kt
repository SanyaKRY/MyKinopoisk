package com.my.kinopoisk.features.mainscreen.data.datasource.api

import com.my.kinopoisk.features.mainscreen.data.datasource.api.retrofit.FilmsApiService
import javax.inject.Inject

class FilmsNetworkDataSource @Inject constructor(
    private val filmsApiService: FilmsApiService
) {
}