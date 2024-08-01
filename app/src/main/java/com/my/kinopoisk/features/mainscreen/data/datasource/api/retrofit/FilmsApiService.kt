package com.my.kinopoisk.features.mainscreen.data.datasource.api.retrofit

import com.my.kinopoisk.BuildConfig
import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.FilmsApi
import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.SearchFilmsApi
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FilmsApiService {

    @Headers("X-API-KEY: ${BuildConfig.X_API_KEY}")
    @GET("/api/v2.2/films/collections")
    suspend fun getListOfFilms(
        @Query("page") page: Int,
        @Query("type") type: String = "TOP_250_MOVIES"
    ): FilmsApi

    @Headers("X-API-KEY: ${BuildConfig.X_API_KEY}")
    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun searchFilmByKey(
        @Query("keyword") keyword: String
    ): SearchFilmsApi
}