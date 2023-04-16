package com.fgarcia.kotlinstars.domain.model

data class ItemStar(
    val name: String,
    val totalStars: Long,
    val totalForks: Long,
    val photoUrl: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemStar

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

