package com.fgarcia.kotlinstars.frameworks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fgarcia.kotlinstars.data.local.DataBaseConstants

@Entity(tableName = DataBaseConstants.STAR_TABLE_NAME)
data class ItemStarEntity(
    @PrimaryKey(autoGenerate = true) val autoId: Int = 0,
    @ColumnInfo(name = DataBaseConstants.STAR_COLUMN_INFO_NAME) val name: String,
    @ColumnInfo(name = DataBaseConstants.STAR_COLUMN_INFO_TOTAL_STARS) val totalStars: Long,
    @ColumnInfo(name = DataBaseConstants.STAR_COLUMN_INFO_TOTAL_FORKS) val totalForks: Long,
    @ColumnInfo(name = DataBaseConstants.STAR_COLUMN_INFO_PHOTO_URL) val photoUrl: String
)