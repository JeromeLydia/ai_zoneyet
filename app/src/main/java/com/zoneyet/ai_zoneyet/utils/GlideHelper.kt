package com.zoneyet.ai_zoneyet.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object GlideHelper {

    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    fun loadCircleImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }

    fun loadRoundCornerImage(
        context: Context,
        url: String,
        imageView: ImageView,
        radius: Int
    ) {
        Glide.with(context)
            .load(url)
            .transform(RoundedCorners(radius))
            .into(imageView)
    }

    fun loadGif(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .asGif()
            .load(url)
            .into(imageView)
    }

    fun loadThumbnail(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .thumbnail(0.1f)
            .into(imageView)
    }
}
