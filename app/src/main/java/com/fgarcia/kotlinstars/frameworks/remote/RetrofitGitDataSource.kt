package com.fgarcia.kotlinstars.frameworks.remote

import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.network.GitHubApi
import com.fgarcia.kotlinstars.frameworks.network.response.toAuthorModel
import com.fgarcia.kotlinstars.frameworks.network.response.toItemStarModel
import javax.inject.Inject

class RetrofitGitDataSource @Inject constructor(
    private val api: GitHubApi
) : GitRemoteDataSource {

    override suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar> {
        return api.getCharacters(queries).items.map {
            it.toItemStarModel()
       }
    }

    override suspend fun fetchLoginAuthor(login: String) = api.getDataLogin(login).toAuthorModel()

}