package com.fgarcia.list.frameworks.di

import com.fgarcia.list.data.remote.ListRemoteDataSource
import com.fgarcia.list.data.repository.ListRepository
import com.fgarcia.list.data.repository.ListRepositoryImpl
import com.fgarcia.list.frameworks.remote.RetrofitListDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindStarRepository(repository: ListRepositoryImpl): ListRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitListDataSource): ListRemoteDataSource

}
