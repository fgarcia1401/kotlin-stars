package com.fgarcia.kotlinstars.frameworks.di

import android.content.Context
import androidx.room.Room
import com.fgarcia.kotlinstars.data.local.DataBaseConstants
import com.fgarcia.kotlinstars.frameworks.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DataBaseConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun provideItemStarDao(appDatabase: AppDatabase) = appDatabase.itemStarDao()

    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase) = appDatabase.remoteKeyDao()
}