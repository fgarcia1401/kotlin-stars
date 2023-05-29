package com.fgarcia.list.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fgarcia.list.domain.model.ItemStar

object PagingSourceFactory {

    fun create(heroes: List<ItemStar>) = object : PagingSource<Int, ItemStar>() {

        override fun getRefreshKey(state: PagingState<Int, ItemStar>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemStar> {
            return LoadResult.Page(
                data = heroes,
                prevKey = null,
                nextKey = 20
            )
        }

    }
}
