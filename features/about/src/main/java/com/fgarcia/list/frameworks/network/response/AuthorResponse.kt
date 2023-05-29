package com.fgarcia.list.frameworks.network.response

import com.google.gson.annotations.SerializedName

data class AuthorResponse(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)


