package com.fgarcia.kotlinstars.frameworks.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Success
import androidx.room.withTransaction
import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.frameworks.database.AppDatabase
import com.fgarcia.kotlinstars.frameworks.database.entity.ItemStarEntity
import com.fgarcia.kotlinstars.frameworks.database.entity.RemoteKeyEntity
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class StarsRemoteMediator @Inject constructor(
    private val database: AppDatabase,
    private val remoteDataSource: GitRemoteDataSource
) : RemoteMediator<Int, ItemStarEntity>() {

    private val itemStarDao = database.itemStarDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemStarEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> FIRST_PAGE
                LoadType.PREPEND ->
                    return Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey()
                    }

                    if (remoteKey.nextPage == null) {
                        return Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextPage
                }
            }

            val queries = hashMapOf(
                PAGE_KEY to page.toString(),
                KEY_LANGUAGE to LANGUAGE_VALUE,
                KEY_SORT to SORT_VALUE
            )

            val paging = remoteDataSource.fetchListStar(queries)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearAll()
                    itemStarDao.clearAll()
                }

                remoteKeyDao.insertOrReplace(RemoteKeyEntity(nextPage = page + 1))
                val entities = paging.map { createItemStartEntity(it) }
                itemStarDao.insertAll(entities)
            }

            Success(endOfPaginationReached = paging.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

    private fun createItemStartEntity(it: ItemStar) =
        ItemStarEntity(
            name = it.name,
            totalStars = it.totalStars,
            totalForks = it.totalForks,
            photoUrl = it.photoUrl
        )

    private companion object {
        const val FIRST_PAGE = 1
        const val PAGE_KEY = "page"
        const val KEY_LANGUAGE = "q"
        const val LANGUAGE_VALUE = "language:kotlin"
        const val KEY_SORT = "sort"
        const val SORT_VALUE = "stars"
    }

}