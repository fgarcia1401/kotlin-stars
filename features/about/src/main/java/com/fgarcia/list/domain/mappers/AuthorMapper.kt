package com.fgarcia.list.domain.mappers

import com.fgarcia.list.domain.model.Author
import com.fgarcia.common.arch.BaseMapper
import com.fgarcia.list.frameworks.network.response.AuthorResponse

object AuthorMapper : BaseMapper<AuthorResponse, Author>() {

    override fun transform(entity: AuthorResponse) = Author(
        login = entity.login,
        photoUrl = entity.avatarUrl
    )

}
