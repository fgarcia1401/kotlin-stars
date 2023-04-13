package com.fgarcia.kotlinstars.data.repository

import androidx.paging.PagingSource
import com.fgarcia.kotlinstars.domain.model.ItemStar

interface StarRepository {

    fun getListStarsWithMostStars(): PagingSource<Int, ItemStar>

}