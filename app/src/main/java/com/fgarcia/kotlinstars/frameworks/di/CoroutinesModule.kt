package com.fgarcia.kotlinstars.frameworks.di

import com.fgarcia.core.coroutines.AppCoroutinesDispatchers
import com.fgarcia.core.coroutines.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun bindDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}