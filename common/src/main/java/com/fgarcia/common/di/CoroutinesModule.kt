package com.fgarcia.common.di

import com.fgarcia.common.arch.coroutines.AppCoroutinesDispatchers
import com.fgarcia.common.arch.coroutines.CoroutinesDispatchers
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