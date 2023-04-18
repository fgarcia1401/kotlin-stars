package com.fgarcia.kotlinstars.frameworks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fgarcia.kotlinstars.frameworks.database.dao.ItemStarDao
import com.fgarcia.kotlinstars.frameworks.database.dao.RemoteKeyDao
import com.fgarcia.kotlinstars.frameworks.database.entity.ItemStarEntity
import com.fgarcia.kotlinstars.frameworks.database.entity.RemoteKeyEntity

@Database(
    entities = [
        ItemStarEntity::class,
        RemoteKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemStarDao(): ItemStarDao

    abstract fun remoteKeyDao(): RemoteKeyDao

}