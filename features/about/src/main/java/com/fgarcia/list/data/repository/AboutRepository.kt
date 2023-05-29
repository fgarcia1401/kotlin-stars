package com.fgarcia.list.data.repository

import com.fgarcia.list.domain.model.Author

interface AboutRepository {

    suspend fun getDataAuthor(login: String): Author

}
