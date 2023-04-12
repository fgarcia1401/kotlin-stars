package com.fgarcia.kotlinstars.frameworks.network.response

import com.google.gson.annotations.SerializedName

data class OwnerItemResponse(
    @SerializedName("avatar_url") val avatarUrl: String,
    val login: String
)
