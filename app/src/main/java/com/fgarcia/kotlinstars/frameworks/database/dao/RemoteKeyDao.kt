package com.fgarcia.kotlinstars.frameworks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fgarcia.kotlinstars.data.local.DataBaseConstants
import com.fgarcia.kotlinstars.frameworks.database.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM ${DataBaseConstants.REMOTE_KEYS_TABLE_NAME}")
    suspend fun remoteKey(): RemoteKeyEntity

    @Query("DELETE FROM ${DataBaseConstants.REMOTE_KEYS_TABLE_NAME}")
    suspend fun clearAll()

}