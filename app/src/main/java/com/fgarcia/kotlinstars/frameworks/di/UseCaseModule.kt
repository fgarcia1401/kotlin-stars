package com.fgarcia.kotlinstars.frameworks.di

import com.fgarcia.kotlinstars.presentation.about.usecase.GetAuthorUseCase
import com.fgarcia.kotlinstars.presentation.about.usecase.GetAuthorUseCaseImpl
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
    fun bindListStarUseCase(useCase: GetListStartUseCaseImpl): GetListStartUseCase

    @Binds
    fun bindAuthorUseCase(useCase: GetAuthorUseCaseImpl): GetAuthorUseCase

}