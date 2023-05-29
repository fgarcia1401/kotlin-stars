package com.fgarcia.list.data.repository

import com.fgarcia.list.data.remote.ListRemoteDataSource
import com.fgarcia.list.frameworks.paging.StarPagingSource
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val remoteDataSource: ListRemoteDataSource
) : ListRepository {

    override fun getListStarsWithMostStars() = StarPagingSource(remoteDataSource)

}
