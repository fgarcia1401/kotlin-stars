package com.fgarcia.common.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.fgarcia.common.R

interface ImageLoader {

    fun load(
        image: ImageView,
        imageUrl: String,
        @DrawableRes placeholder: Int = R.drawable.ic_img_placeholder,
        @DrawableRes fallback: Int = R.drawable.ic_img_loading_error
    )

}