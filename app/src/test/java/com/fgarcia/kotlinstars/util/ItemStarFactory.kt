package com.fgarcia.kotlinstars.util

import com.fgarcia.kotlinstars.domain.model.ItemStar

object ItemStarFactory {

    private val itemKotlin = ItemStar(
        name = "JetBrains / kotlin",
        totalStars = 44417,
        totalForks = 5494,
        photoUrl = "https://avatars.githubusercontent.com/u/878437?v=4"
    )

    private val itemSquare = ItemStar(
        name = "square / okhttp",
        totalStars = 43835,
        totalForks = 9073,
        photoUrl = "https://avatars.githubusercontent.com/u/82592?v=4"
    )

    fun create() = listOf(itemKotlin, itemSquare)

}