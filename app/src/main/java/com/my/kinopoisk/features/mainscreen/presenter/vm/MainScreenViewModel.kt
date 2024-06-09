package com.my.kinopoisk.features.mainscreen.presenter.vm

import androidx.lifecycle.ViewModel
import com.my.kinopoisk.features.mainscreen.domain.usecase.GetListOfFilmsUseCase
import com.my.kinopoisk.features.mainscreen.presenter.model.MainScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getListOfFilmsUseCase: GetListOfFilmsUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val stateFlow: Flow<MainScreenState>
        get() = _stateFlow

}