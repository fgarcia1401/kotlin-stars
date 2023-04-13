package com.fgarcia.kotlinstars.frameworks.remote

import com.fgarcia.kotlinstars.data.remote.StarRemoteDataSource
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.network.GitHubApi
import com.fgarcia.kotlinstars.frameworks.network.response.toItemStarModel
import javax.inject.Inject

class RetrofitStarDataSource @Inject constructor(
    private val api: GitHubApi
) : StarRemoteDataSource {

    override suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar> {
        val data = api.getCharacters(queries)
        return data.items.map {
            it.toItemStarModel()
       }
    }

}