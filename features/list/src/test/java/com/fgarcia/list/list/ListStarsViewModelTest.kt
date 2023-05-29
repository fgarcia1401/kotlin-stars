package com.fgarcia.list.list

import androidx.paging.PagingData
import com.fgarcia.commontest.coroutine.MainCoroutineRule
import com.fgarcia.list.domain.usecase.GetListStartUseCase
import com.fgarcia.list.util.ItemStarFactory
import com.fgarcia.list.presentation.list.ListStarsViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListStarsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getListStartUseCase: GetListStartUseCase

    private lateinit var listStarsViewModel: ListStarsViewModel

    private val pagingDataCharacters = PagingData.from(ItemStarFactory.create())

    @Before
    fun setUp() {
        listStarsViewModel = ListStarsViewModel(getListStartUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate paging data object values when calling listStarsPagingData`() = runTest {

        whenever(
            getListStartUseCase.invoke(any())
        ).thenReturn(
            flowOf(pagingDataCharacters)
        )

        val result = listStarsViewModel.listStarsPagingData()

        Assert.assertNotNull(result.first())
    }

    @ExperimentalCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`()
            = runTest {
        whenever(
            getListStartUseCase.invoke(any())
        ).thenThrow(RuntimeException())

        listStarsViewModel.listStarsPagingData()
    }

}

