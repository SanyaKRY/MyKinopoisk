package com.my.kinopoisk.features.mainscreen.domain.usecase

import androidx.paging.PagingData
import com.my.kinopoisk.features.mainscreen.domain.model.FilmDomain
import com.my.kinopoisk.features.mainscreen.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListOfFilmsUseCase @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) {

    suspend fun execute(): Flow<PagingData<FilmDomain>> {
        return mainScreenRepository.getListOfFilms()
    }
}