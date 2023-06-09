package com.fgarcia.common.di

import com.fgarcia.common.imageloader.GlideImageLoader
import com.fgarcia.common.imageloader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface CommonModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader

}