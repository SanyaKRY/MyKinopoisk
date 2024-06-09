package com.my.kinopoisk.features.mainscreen.presenter.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.my.kinopoisk.databinding.FilmItemBinding
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi

class FilmAdapter constructor() : ListAdapter<FilmUi, ViewHolderFilm>(
    FilmDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilm {
        val itemViewHolder = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderFilm(itemViewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderFilm, position: Int) {
        holder.apply {
            val current: FilmUi = getItem(position)
            bind(current)
        }
    }
}