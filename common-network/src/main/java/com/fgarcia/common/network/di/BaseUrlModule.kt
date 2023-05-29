package com.fgarcia.common.network.di

import com.fgarcia.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

}
