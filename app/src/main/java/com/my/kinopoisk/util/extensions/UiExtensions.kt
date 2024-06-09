package com.my.kinopoisk.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.my.kinopoisk.R

fun ImageView.loadImage(imageUri: String) {
    Glide.with(context)
        .load(imageUri)
        .placeholder(R.drawable.default_image)
        .error(R.drawable.default_image)
        .into(this)
}