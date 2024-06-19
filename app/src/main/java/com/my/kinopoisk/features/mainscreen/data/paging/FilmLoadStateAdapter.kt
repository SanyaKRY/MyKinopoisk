package com.my.kinopoisk.features.mainscreen.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.kinopoisk.databinding.LoadStateBinding

class FilmLoadStateAdapter : LoadStateAdapter<FilmLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val itemViewHolder = LoadStateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(itemViewHolder)
    }

    class LoadStateViewHolder(
        private val binding: LoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}