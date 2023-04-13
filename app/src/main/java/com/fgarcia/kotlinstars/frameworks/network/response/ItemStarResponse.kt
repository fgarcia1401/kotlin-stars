package com.fgarcia.kotlinstars.frameworks.network.response

import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.google.gson.annotations.SerializedName

data class ItemRepositoryResponse(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count") val totalStars: Int,
    @SerializedName("forks_count") val totalForks: Int,
    val owner: OwnerItemResponse
)

fun ItemRepositoryResponse.toItemStarModel() = ItemStar(
    name = this.name,
    totalStars = this.totalStars.toString(),
    totalForks = this.totalForks.toString(),
    photoUrl = this.owner.avatarUrl,
    author = this.owner.login
)

