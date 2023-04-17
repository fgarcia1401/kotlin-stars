package com.fgarcia.kotlinstars.presentation.list.usecase

import androidx.paging.PagingConfig
import com.fgarcia.commontest.coroutine.MainCoroutineRule
import com.fgarcia.kotlinstars.data.repository.GitRepository
import com.fgarcia.kotlinstars.presentation.list.usecase.GetListStartUseCase.GetListStarParams
import com.fgarcia.kotlinstars.util.ItemStarFactory
import com.fgarcia.kotlinstars.util.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class GetListStartUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: GitRepository

    private lateinit var getListStartUseCase: GetListStartUseCase

    @Before
    fun setUp() {
        getListStartUseCase = GetListStartUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            val fakePagingSource = PagingSourceFactory.create(ItemStarFactory.create())

            whenever(repository.getListStarsWithMostStars()).thenReturn(fakePagingSource)

            val result = getListStartUseCase.invoke(GetListStarParams(PagingConfig(30)))

            verify(repository).getListStarsWithMostStars()
            Assert.assertNotNull(result.first())
        }

    @Test(expected = RuntimeException::class)
    fun `should validate error when invoke from use case is called`() =
        runTest {
            whenever(repository.getListStarsWithMostStars()).thenThrow(RuntimeException())

            getListStartUseCase.invoke(GetListStarParams(PagingConfig(30)))

            verify(repository).getListStarsWithMostStars()
        }

}