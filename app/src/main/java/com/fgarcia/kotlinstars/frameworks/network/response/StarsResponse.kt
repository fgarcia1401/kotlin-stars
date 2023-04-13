package com.fgarcia.kotlinstars.frameworks.network.response

import com.google.gson.annotations.SerializedName

data class StarsResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    val items: List<ItemRepositoryResponse>
)


