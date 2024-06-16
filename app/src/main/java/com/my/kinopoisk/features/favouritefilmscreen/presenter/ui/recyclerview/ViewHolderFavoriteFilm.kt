package com.my.kinopoisk.features.favouritefilmscreen.presenter.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.my.kinopoisk.R
import com.my.kinopoisk.databinding.FilmItemBinding
import com.my.kinopoisk.features.favouritefilmscreen.presenter.model.FavoriteFilmUi
import com.my.kinopoisk.util.extensions.loadImage

class ViewHolderFavoriteFilm(
    private val binding: FilmItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(filmUi: FavoriteFilmUi) {
        binding.filmName.text = filmUi.nameRu
        val year = filmUi.year
        val genres = filmUi.genres
        val filmGenreAndYear = "$genres ($year)"
        binding.filmGenreAndYear.text = filmGenreAndYear
        binding.filmImage.loadImage(filmUi.posterUrl)
        binding.mark.visibility = View.VISIBLE
        binding.mark.setImageResource(R.drawable.hand_like)
    }
}