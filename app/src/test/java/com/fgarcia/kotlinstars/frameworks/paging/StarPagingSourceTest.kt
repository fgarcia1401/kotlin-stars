package com.fgarcia.kotlinstars.frameworks.paging

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.fgarcia.commontest.coroutine.MainCoroutineRule
import com.fgarcia.kotlinstars.data.remote.GitRemoteDataSource
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.util.ItemStarFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StarPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: GitRemoteDataSource

    private lateinit var startPagingSource: StarPagingSource

    @Before
    fun setUp() {
        startPagingSource = StarPagingSource(remoteDataSource)
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        val items = ItemStarFactory.create()
        whenever(remoteDataSource.fetchListStar(any())).thenReturn(items)

        val result = startPagingSource.load(
            LoadParams.Refresh(
                key= null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = 2
            ),
            result
        )
    }

    @Test
    fun `should return empty list result when return from remote source is empty`() = runTest {
        whenever(remoteDataSource.fetchListStar(any())).thenReturn(emptyList())

        val resultActual = startPagingSource.load(
            LoadParams.Refresh(
                key= null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            ),
            resultActual
        )
    }

    @Test
    fun `should return a error load result when load is called`() =
        runTest {
            val exception = RuntimeException()
            whenever(remoteDataSource.fetchListStar(any())).thenThrow(exception)

            val resultActual = startPagingSource.load(
                LoadParams.Refresh(
                    key= null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )

            assertEquals(
                LoadResult.Error<Int, ItemStar>(exception),
                resultActual
            )
        }

}