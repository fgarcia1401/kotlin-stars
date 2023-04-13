package com.fgarcia.kotlinstars.frameworks.network

import com.fgarcia.kotlinstars.frameworks.network.response.StarsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GitHubApi {

    @GET(SEARCH)
    suspend fun getCharacters(
        @QueryMap queries: Map<String, String>
    ): StarsResponse

    private companion object {
        const val SEARCH = "search/repositories"
    }

}