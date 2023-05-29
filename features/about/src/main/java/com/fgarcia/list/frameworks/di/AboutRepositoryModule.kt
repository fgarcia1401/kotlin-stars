package com.fgarcia.list.frameworks.di

import com.fgarcia.list.data.remote.AboutDataSource
import com.fgarcia.list.data.repository.AboutRepository
import com.fgarcia.list.data.repository.AboutRepositoryImpl
import com.fgarcia.list.frameworks.remote.RetrofitGitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AboutRepositoryModule {

    @Binds
    fun bindAboutRepository(repository: AboutRepositoryImpl): AboutRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitGitDataSource): AboutDataSource

}
