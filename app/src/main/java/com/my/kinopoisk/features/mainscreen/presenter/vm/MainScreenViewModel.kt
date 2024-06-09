package com.my.kinopoisk.features.mainscreen.presenter.vm

import androidx.lifecycle.ViewModel
import com.my.kinopoisk.features.mainscreen.domain.usecase.GetListOfFilmsUseCase
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getListOfFilmsUseCase: GetListOfFilmsUseCase
) : ViewModel() {

}