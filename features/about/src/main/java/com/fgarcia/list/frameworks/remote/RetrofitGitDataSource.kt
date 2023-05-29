package com.fgarcia.list.frameworks.remote

import com.fgarcia.list.data.remote.AboutDataSource
import com.fgarcia.list.domain.mappers.AuthorMapper
import com.fgarcia.list.domain.model.Author
import com.fgarcia.list.frameworks.network.GitHubApi
import javax.inject.Inject

class RetrofitGitDataSource @Inject constructor(
    private val api: GitHubApi
) : AboutDataSource {

    override suspend fun fetchLoginAuthor(login: String): Author {
        return AuthorMapper.transform(api.getDataLogin(login))
    }

}
