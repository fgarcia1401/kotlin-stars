package com.fgarcia.kotlinstars.frameworks.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fgarcia.kotlinstars.data.local.DataBaseConstants
import com.fgarcia.kotlinstars.frameworks.database.entity.ItemStarEntity

@Dao
interface ItemStarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemStar: List<ItemStarEntity>)

    @Query("SELECT * FROM ${DataBaseConstants.STAR_TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, ItemStarEntity>

    @Query("DELETE from ${DataBaseConstants.STAR_TABLE_NAME}")
    suspend fun clearAll()

}