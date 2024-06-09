package com.my.kinopoisk.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.my.kinopoisk.R

fun ImageView.loadImage(imageUri: String) {
    Glide.with(context)
        .load(imageUri)
        .placeholder(R.drawable.default_image)
        .error(R.drawable.default_image)
        .override(200, SIZE_ORIGINAL)
        .into(this)
}