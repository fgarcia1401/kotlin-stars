package com.fgarcia.kotlinstars.data.remote

import com.fgarcia.kotlinstars.domain.model.Author
import com.fgarcia.kotlinstars.domain.model.ItemStar

interface GitRemoteDataSource {

    suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar>

    suspend fun fetchLoginAuthor(login: String) : Author

}