package com.fgarcia.kotlinstars.frameworks.network.response

import com.google.gson.annotations.SerializedName

data class ItemRepositoryResponse(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count") val totalStars: Int,
    @SerializedName("forks_count") val totalForks: Int,
    val owner: OwnerItemResponse
)