package com.fgarcia.kotlinstars.frameworks.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fgarcia.kotlinstars.data.local.DataBaseConstants

@Entity(tableName = DataBaseConstants.REMOTE_KEYS_TABLE_NAME)
data class RemoteKeyEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = DataBaseConstants.REMOTE_KEYS_COLUMN_INFO_PAGE) val nextPage: Int? = null
)