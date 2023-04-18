package com.fgarcia.kotlinstars.frameworks.network.response

import com.google.gson.annotations.SerializedName

data class ItemStarResponse(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count") val totalStars: Long,
    @SerializedName("forks_count") val totalForks: Long,
    val owner: OwnerItemResponse
)

