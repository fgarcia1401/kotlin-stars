package com.fgarcia.kotlinstars.util

import android.view.View
import com.fgarcia.kotlinstars.frameworks.network.response.ItemRepositoryResponse

typealias OnCharacterItemClick = (item: ItemRepositoryResponse, view: View) -> Unit