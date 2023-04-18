package com.fgarcia.kotlinstars.domain.mappers

import com.fgarcia.common.arch.BaseMapper
import com.fgarcia.kotlinstars.domain.model.Author
import com.fgarcia.kotlinstars.frameworks.network.response.AuthorResponse

object AuthorMapper : BaseMapper<AuthorResponse, Author>() {

    override fun transform(entity: AuthorResponse) = Author(
        login = entity.login,
        photoUrl = entity.avatarUrl
    )

}