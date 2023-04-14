package com.fgarcia.kotlinstars.frameworks.network.response

import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.google.gson.annotations.SerializedName

data class ItemRepositoryResponse(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count") val totalStars: Long,
    @SerializedName("forks_count") val totalForks: Long,
    val owner: OwnerItemResponse
)

fun ItemRepositoryResponse.toItemStarModel() = ItemStar(
    name = this.name,
    totalStars = this.totalStars,
    totalForks = this.totalForks,
    photoUrl = this.owner.avatarUrl,
    author = this.owner.login
)

