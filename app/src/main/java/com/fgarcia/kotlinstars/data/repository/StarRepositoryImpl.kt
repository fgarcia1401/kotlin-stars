package com.fgarcia.kotlinstars.data.repository

import androidx.paging.PagingSource
import com.fgarcia.kotlinstars.data.remote.StarRemoteDataSource
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.paging.StarPagingSource
import javax.inject.Inject

class StarRepositoryImpl @Inject constructor(
    private val remoteDataSource: StarRemoteDataSource
) : StarRepository {

    override fun getListStarsWithMostStars(): PagingSource<Int, ItemStar> {
        return StarPagingSource(remoteDataSource)
    }

}