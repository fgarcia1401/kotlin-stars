package com.fgarcia.kotlinstars.frameworks.remote

import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.domain.mappers.AuthorMapper
import com.fgarcia.kotlinstars.domain.mappers.ItemStarMapper
import com.fgarcia.kotlinstars.domain.model.Author
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.network.GitHubApi
import javax.inject.Inject

class RetrofitGitDataSource @Inject constructor(
    private val api: GitHubApi
) : GitRemoteDataSource {

    override suspend fun fetchListStar(queries: Map<String, String>) : List<ItemStar> {
        return api.getCharacters(queries).items.map { ItemStarMapper.transform(it) }
    }

    override suspend fun fetchLoginAuthor(login: String): Author {
        return AuthorMapper.transform(api.getDataLogin(login))
    }

}