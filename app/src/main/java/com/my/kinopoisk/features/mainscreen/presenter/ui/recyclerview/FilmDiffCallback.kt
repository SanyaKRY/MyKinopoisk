package com.my.kinopoisk.features.mainscreen.presenter.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi

class FilmDiffCallback : DiffUtil.ItemCallback<FilmUi>() {

    override fun areItemsTheSame(oldItem: FilmUi, newItem: FilmUi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: FilmUi, newItem: FilmUi): Boolean {
        return oldItem == newItem
    }
}