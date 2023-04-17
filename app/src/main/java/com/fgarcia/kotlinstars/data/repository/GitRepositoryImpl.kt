package com.fgarcia.kotlinstars.data.repository

import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.frameworks.paging.StarPagingSource
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val remoteDataSource: GitRemoteDataSource
) : GitRepository {

    override fun getListStarsWithMostStars() = StarPagingSource(remoteDataSource)

    override suspend fun getDataAuthor(login: String) = remoteDataSource.fetchLoginAuthor(login)

}