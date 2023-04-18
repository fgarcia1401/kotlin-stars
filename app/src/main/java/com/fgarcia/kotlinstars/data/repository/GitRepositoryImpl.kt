package com.fgarcia.kotlinstars.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.database.AppDatabase
import com.fgarcia.kotlinstars.frameworks.database.entity.ItemStarEntity
import com.fgarcia.kotlinstars.frameworks.paging.StarPagingSource
import com.fgarcia.kotlinstars.frameworks.paging.StarsRemoteMediator
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class GitRepositoryImpl @Inject constructor(
    private val remoteDataSource: GitRemoteDataSource,
    private val database: AppDatabase
) : GitRepository {

    override fun getListStarsWithMostStars() = StarPagingSource(remoteDataSource)

    override suspend fun getDataAuthor(login: String) = remoteDataSource.fetchLoginAuthor(login)

    override fun getCachedListStarsWithMostStars(
        pagingConfig: PagingConfig
    ): Flow<PagingData<ItemStar>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = StarsRemoteMediator(database, remoteDataSource)
        ) {
            database.itemStarDao().pagingSource()
        }.flow.map { pagingData ->
            pagingData.map { createItemStar(it) }
        }
    }

    private fun createItemStar(it: ItemStarEntity) =
        ItemStar(
            name = it.name,
            totalStars = it.totalStars,
            totalForks = it.totalForks,
            photoUrl = it.photoUrl
        )

}