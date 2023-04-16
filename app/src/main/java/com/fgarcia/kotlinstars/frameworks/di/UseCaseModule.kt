package com.fgarcia.kotlinstars.frameworks.di

import com.fgarcia.kotlinstars.presentation.list.usecase.GetListStartUseCase
import com.fgarcia.kotlinstars.presentation.list.usecase.GetListStartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(useCase: GetListStartUseCaseImpl): GetListStartUseCase

}