package com.fgarcia.list.data.remote

import com.fgarcia.list.domain.model.ItemStar

interface ListRemoteDataSource {

    suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar>

}
