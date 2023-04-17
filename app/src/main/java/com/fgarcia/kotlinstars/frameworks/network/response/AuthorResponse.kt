package com.fgarcia.kotlinstars.frameworks.network.response

import com.fgarcia.kotlinstars.domain.model.Author
import com.google.gson.annotations.SerializedName

data class AuthorResponse(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

fun AuthorResponse.toAuthorModel() = Author(
    login = this.login,
    photoUrl = this.avatarUrl
)

