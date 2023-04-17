package com.fgarcia.kotlinstars.frameworks.di

import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.data.repository.GitRepository
import com.fgarcia.kotlinstars.data.repository.GitRepositoryImpl
import com.fgarcia.kotlinstars.frameworks.remote.RetrofitGitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindStarRepository(repository: GitRepositoryImpl): GitRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitGitDataSource):  GitRemoteDataSource

}