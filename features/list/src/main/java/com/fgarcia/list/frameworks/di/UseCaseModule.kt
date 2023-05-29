package com.fgarcia.list.frameworks.di

import com.fgarcia.list.domain.usecase.GetListStartUseCase
import com.fgarcia.list.domain.usecase.GetListStartUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindListStarUseCase(useCase: GetListStartUseCaseImpl): GetListStartUseCase

}
