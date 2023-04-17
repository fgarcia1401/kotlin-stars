package com.fgarcia.kotlinstars.frameworks.network

import com.fgarcia.kotlinstars.frameworks.network.response.AuthorResponse
import com.fgarcia.kotlinstars.frameworks.network.response.StarsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GitHubApi {

    @GET(SEARCH)
    suspend fun getCharacters(
        @QueryMap queries: Map<String, String>
    ): StarsResponse

    @GET(DATA_LOGIN)
    suspend fun getDataLogin(
        @Path("login") login: String
    ): AuthorResponse

    private companion object {
        const val SEARCH = "search/repositories"
        const val DATA_LOGIN = "users/{login}"
    }

}