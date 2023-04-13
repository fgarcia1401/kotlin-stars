package com.fgarcia.kotlinstars.data.remote

import com.fgarcia.kotlinstars.domain.model.ItemStar

interface StarRemoteDataSource {

    suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar>

}