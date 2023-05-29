package com.fgarcia.list.frameworks.network

import com.fgarcia.list.frameworks.network.response.StarsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GitRepositoriesApi {

    @GET(SEARCH)
    suspend fun getRepositories(
        @QueryMap queries: Map<String, String>
    ): StarsResponse

    private companion object {
        const val SEARCH = "search/repositories"
    }

}
