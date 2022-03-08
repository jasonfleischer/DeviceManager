package com.example.devicemanager.common.data_binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.devicemanager.R

@BindingAdapter("circularImageUrl")
fun circularImageUrl(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .fitCenter()
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_placeholder)
        .into(view)
}

@BindingAdapter("roundedImageUrl")
fun roundedImageUrl(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .fitCenter()
        .transform(CenterInside(), RoundedCorners(7))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_placeholder)
        .into(view)
}

