package com.fgarcia.common.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {

    override fun load(
        image: ImageView,
        imageUrl: String,
        placeholder: Int,
        fallback: Int
    ) {
        Glide.with(image.rootView)
            .load(imageUrl)
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .fallback(fallback)
            .into(image)
    }

}