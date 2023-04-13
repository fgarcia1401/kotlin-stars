package com.fgarcia.kotlinstars.frameworks.di

import com.fgarcia.kotlinstars.data.remote.StarRemoteDataSource
import com.fgarcia.kotlinstars.data.repository.StarRepository
import com.fgarcia.kotlinstars.data.repository.StarRepositoryImpl
import com.fgarcia.kotlinstars.frameworks.remote.RetrofitStarDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindStarRepository(repository: StarRepositoryImpl): StarRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitStarDataSource):  StarRemoteDataSource

}