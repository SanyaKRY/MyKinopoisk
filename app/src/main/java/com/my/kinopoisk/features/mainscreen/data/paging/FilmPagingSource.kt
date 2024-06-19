package com.my.kinopoisk.features.mainscreen.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.my.kinopoisk.features.mainscreen.data.datasource.api.FilmsNetworkDataSource
import com.my.kinopoisk.features.mainscreen.data.datasource.api.model.FilmsApi
import com.my.kinopoisk.features.mainscreen.data.repository.mapper.FilmApiToDomainMapper
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain

class FilmPagingSource constructor(
    private val filmsNetworkDataSource: FilmsNetworkDataSource
) : PagingSource<Int, FilmDomain>() {

    override fun getRefreshKey(state: PagingState<Int, FilmDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDomain> {
        return try {


            val pageNumber = params.key ?: 1
            val films: FilmsApi = filmsNetworkDataSource.getListOfFilms(pageNumber)

            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            val nextKey = if (films.films.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(data = FilmApiToDomainMapper.map(films.films), prevKey = prevKey, nextKey = nextKey)



        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}