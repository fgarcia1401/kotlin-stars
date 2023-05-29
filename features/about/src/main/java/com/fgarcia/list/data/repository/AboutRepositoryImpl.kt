package com.fgarcia.list.data.repository

import com.fgarcia.list.data.remote.AboutDataSource
import javax.inject.Inject

class AboutRepositoryImpl @Inject constructor(
    private val remoteDataSource: AboutDataSource
) : AboutRepository {

    override suspend fun getDataAuthor(login: String) = remoteDataSource.fetchLoginAuthor(login)

}
