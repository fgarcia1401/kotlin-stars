package com.fgarcia.kotlinstars.data.repository

import androidx.paging.PagingSource
import com.fgarcia.kotlinstars.domain.model.Author
import com.fgarcia.kotlinstars.domain.model.ItemStar

interface GitRepository {

    fun getListStarsWithMostStars(): PagingSource<Int, ItemStar>

    suspend fun getDataAuthor(login: String): Author

}