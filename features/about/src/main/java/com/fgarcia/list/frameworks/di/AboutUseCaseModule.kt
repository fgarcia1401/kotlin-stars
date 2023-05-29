package com.fgarcia.list.frameworks.di

import com.fgarcia.list.domain.usecase.GetAuthorUseCase
import com.fgarcia.list.domain.usecase.GetAuthorUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AboutUseCaseModule {

    @Binds
    fun bindAuthorUseCase(useCase: GetAuthorUseCaseImpl): GetAuthorUseCase

}