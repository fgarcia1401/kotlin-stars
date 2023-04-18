package com.fgarcia.kotlinstars.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.fgarcia.kotlinstars.domain.model.Author
import com.fgarcia.kotlinstars.domain.model.ItemStar
import kotlinx.coroutines.flow.Flow

interface GitRepository {

    fun getListStarsWithMostStars(): PagingSource<Int, ItemStar>

    fun getCachedListStarsWithMostStars(
        pagingConfig: PagingConfig
    ): Flow<PagingData<ItemStar>>

    suspend fun getDataAuthor(login: String): Author

}