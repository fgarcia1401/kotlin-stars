package com.fgarcia.kotlinstars.domain.mappers

import com.fgarcia.common.arch.BaseMapper
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.network.response.ItemStarResponse

object ItemStarMapper : BaseMapper<ItemStarResponse, ItemStar>() {

    override fun transform(entity: ItemStarResponse) = ItemStar(
        name = "${entity.owner.login} / ${entity.name}",
        totalStars = entity.totalStars,
        totalForks = entity.totalForks,
        photoUrl = entity.owner.avatarUrl,
    )

}