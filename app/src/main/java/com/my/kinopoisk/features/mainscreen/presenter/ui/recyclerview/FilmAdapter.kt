package com.my.kinopoisk.features.mainscreen.presenter.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.kinopoisk.databinding.FilmItemBinding
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi

class FilmAdapter constructor(
    private val insertDeleteFilmListener: (
        filmUi: FilmUi
    ) -> Unit
) : ListAdapter<FilmUi, ViewHolderFilm>(
    FilmDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilm {
        val itemViewHolder = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolderFilm(itemViewHolder)
        setItemListener(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderFilm, position: Int) {
        holder.apply {
            val current: FilmUi = getItem(position)
            bind(current)
        }
    }

    private fun setItemListener(viewHolderFilm: ViewHolderFilm) {
        viewHolderFilm.itemView.setOnLongClickListener {
            val position = viewHolderFilm.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                insertDeleteFilmListener.invoke(getItem(position))
                getItem(position).apply {
                    isSavedToDataBase = !isSavedToDataBase
                }
            }
            true
        }
    }
}