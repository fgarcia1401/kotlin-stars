package com.fgarcia.list.frameworks.remote

import com.fgarcia.list.data.remote.ListRemoteDataSource
import com.fgarcia.list.domain.mappers.ItemStarMapper
import com.fgarcia.list.domain.model.ItemStar
import com.fgarcia.list.frameworks.network.GitRepositoriesApi
import javax.inject.Inject

class RetrofitListDataSource @Inject constructor(
    private val api: GitRepositoriesApi
) : ListRemoteDataSource {

    override suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar> {
        return api.getRepositories(queries).items.map { ItemStarMapper.transform(it) }
    }

}
