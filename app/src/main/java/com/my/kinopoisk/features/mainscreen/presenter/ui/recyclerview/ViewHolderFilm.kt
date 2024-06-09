package com.my.kinopoisk.features.mainscreen.presenter.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.my.kinopoisk.databinding.FilmItemBinding
import com.my.kinopoisk.features.mainscreen.presenter.model.FilmUi

class ViewHolderFilm(private val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(filmUi: FilmUi) {
        binding.filmName.text = filmUi.nameRu
        val year = filmUi.year
        val mainGenre = filmUi.genres[0]
        val filmGenreAndYear = "$mainGenre ($year)"
        binding.filmGenreAndYear.text = filmGenreAndYear
    }
}