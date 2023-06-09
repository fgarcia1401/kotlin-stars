package com.fgarcia.list.frameworks.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fgarcia.list.data.remote.ListRemoteDataSource
import com.fgarcia.list.domain.model.ItemStar
import java.lang.Exception


class StarPagingSource(
    private val remoteDataSource: ListRemoteDataSource,
) : PagingSource<Int, ItemStar>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemStar> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val queries = hashMapOf(
                PAGE_KEY to page.toString(),
                KEY_LANGUAGE to LANGUAGE_VALUE,
                KEY_SORT to SORT_VALUE
            )

            val response = remoteDataSource.fetchListStar(queries)

            LoadResult.Page(
                data = response,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        }catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemStar>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    private companion object {
        const val FIRST_PAGE = 1
        const val PAGE_KEY = "page"
        const val KEY_LANGUAGE = "q"
        const val LANGUAGE_VALUE = "language:kotlin"
        const val KEY_SORT = "sort"
        const val SORT_VALUE = "stars"
        const val LIMIT = 30
    }

}
