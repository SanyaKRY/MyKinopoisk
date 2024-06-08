package com.my.kinopoisk.features.mainscreen.data.datasource.api.retrofit

import com.my.kinopoisk.BuildConfig
import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.FilmsApi
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FilmsApiService {

    @Headers("X-API-KEY: ${BuildConfig.X_API_KEY}")
    @GET("/api/v2.2/films/top")
    suspend fun getListOfFilms(
        @Query("page") page: Int
    ): FilmsApi

    @Headers("X-API-KEY: ${BuildConfig.X_API_KEY}")
    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun searchFilmByKey(
        @Query("keyword") keyword: String
    ): FilmsApi
}