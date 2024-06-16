package com.my.kinopoisk.features.favouritefilmscreen.presenter.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.my.kinopoisk.databinding.FilmItemBinding
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteFilmUi

class FavoriteFilmAdapter constructor() : ListAdapter<FavoriteFilmUi, ViewHolderFavoriteFilm>(
    FavoriteFilmsDiffCallback()
)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoriteFilm {
        val itemViewHolder = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderFavoriteFilm(itemViewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderFavoriteFilm, position: Int) {
        holder.apply {
            val current: FavoriteFilmUi = getItem(position)
            bind(current)
        }
    }
}