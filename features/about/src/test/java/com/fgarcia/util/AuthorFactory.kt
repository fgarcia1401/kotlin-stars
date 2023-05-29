package com.fgarcia.util

import com.fgarcia.list.domain.model.Author

object AuthorFactory {

    fun create() = Author(
        login = "fgarcia1401",
        photoUrl = "https://avatars.githubusercontent.com/u/5015527?v=4"
    )

}
