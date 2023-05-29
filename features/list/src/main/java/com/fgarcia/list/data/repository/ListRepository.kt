package com.fgarcia.list.data.repository

import androidx.paging.PagingSource
import com.fgarcia.list.domain.model.ItemStar

interface ListRepository {

    fun getListStarsWithMostStars(): PagingSource<Int, ItemStar>

}
