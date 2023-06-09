package com.fgarcia.about.usecase

import com.fgarcia.common.arch.usecase.base.ResultStatus
import com.fgarcia.commontest.coroutine.MainCoroutineRule
import com.fgarcia.list.data.repository.AboutRepository
import com.fgarcia.list.domain.usecase.GetAuthorUseCase
import com.fgarcia.list.domain.usecase.GetAuthorUseCase.GetAuthorParams
import com.fgarcia.list.domain.usecase.GetAuthorUseCaseImpl
import com.fgarcia.util.AuthorFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class GetAuthorUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: AboutRepository

    private lateinit var getAuthorUseCase: GetAuthorUseCase

    private val author = AuthorFactory.create()

    @Before
    fun setUp() {
        getAuthorUseCase = GetAuthorUseCaseImpl(
            repository= repository,
            dispatchers = mainCoroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun `should data author when invoke from use case list data author`() =
        runTest {

            whenever(repository.getDataAuthor(any())).thenReturn(author)

            val result = getAuthorUseCase.invoke(GetAuthorParams(login = author.login))

            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList.first())
            assertTrue(resultList[1] is ResultStatus.Success)

            verify(repository).getDataAuthor(author.login)
        }

    @Test
    fun `should validate error when invoke from use case is called`() =
        runTest {
            whenever(repository.getDataAuthor(any())).thenThrow(RuntimeException())

            val result = getAuthorUseCase.invoke(GetAuthorParams(login = author.login))

            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList.first())
            assertTrue(resultList[1] is ResultStatus.Error)

            verify(repository).getDataAuthor(author.login)
        }

}
