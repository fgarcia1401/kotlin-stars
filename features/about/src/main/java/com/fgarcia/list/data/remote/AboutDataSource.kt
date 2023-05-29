package com.fgarcia.list.data.remote

import com.fgarcia.list.domain.model.Author

interface AboutDataSource {

    suspend fun fetchLoginAuthor(login: String) : Author

}
