package com.my.kinopoisk.features.favouritefilmscreen.presenter.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteFilmUi

class FavoriteFilmsDiffCallback : DiffUtil.ItemCallback<FavoriteFilmUi>() {

    override fun areItemsTheSame(oldItem: FavoriteFilmUi, newItem: FavoriteFilmUi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: FavoriteFilmUi, newItem: FavoriteFilmUi): Boolean {
        return oldItem == newItem
    }
}