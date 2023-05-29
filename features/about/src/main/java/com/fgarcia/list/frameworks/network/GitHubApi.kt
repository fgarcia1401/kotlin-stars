package com.fgarcia.list.frameworks.network

import com.fgarcia.list.frameworks.network.response.AuthorResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET(DATA_LOGIN)
    suspend fun getDataLogin(
        @Path("login") login: String
    ): AuthorResponse

    private companion object {
        const val DATA_LOGIN = "users/{login}"
    }

}

