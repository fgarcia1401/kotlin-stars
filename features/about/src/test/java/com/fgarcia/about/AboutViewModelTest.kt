package com.fgarcia.about

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fgarcia.list.domain.model.Author
import com.fgarcia.list.domain.usecase.GetAuthorUseCase
import com.fgarcia.list.domain.usecase.GetAuthorUseCase.GetAuthorParams
import com.fgarcia.list.presentation.AboutViewModel
import com.fgarcia.common.arch.usecase.base.ResultStatus
import com.fgarcia.commontest.coroutine.MainCoroutineRule
import com.fgarcia.util.AuthorFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AboutViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getAuthorUseCase: GetAuthorUseCase

    private lateinit var aboutViewModel: AboutViewModel

    private val author = AuthorFactory.create()

    @Mock
    private lateinit var uiStateObserver: Observer<ResultStatus<Author>>

    @Before
    fun setUp() {
        aboutViewModel = AboutViewModel(getAuthorUseCase)
        aboutViewModel.uiState.asLiveData().observeForever(uiStateObserver)
    }

    @Test
    fun `should notify uiState with Loading and Success from UiState when get data author returns success`() =
        runTest {
        //Arrange
        whenever(getAuthorUseCase.invoke(any()))
            .thenReturn(
                flowOf(
                    ResultStatus.Success(author)
                )
            )

        //Action
        aboutViewModel.getDataAuthor()

        //Assert
        verify(getAuthorUseCase).invoke(GetAuthorParams(author.login))
        verify(uiStateObserver).onChanged(ResultStatus.Loading)
        verify(uiStateObserver).onChanged(ResultStatus.Success(author))
    }

    @Test
    fun `should notify uiState with Loading, Error from UiState when get data author returns an exception`() = runTest {
        //Arrange
        val error = Throwable()
        whenever(getAuthorUseCase.invoke(any()))
            .thenReturn(
                flowOf(ResultStatus.Error(error))
            )

        //Action
        aboutViewModel.getDataAuthor()

        //Assert
        verify(getAuthorUseCase).invoke(GetAuthorParams(author.login))
        verify(uiStateObserver).onChanged(ResultStatus.Loading)
        verify(uiStateObserver).onChanged(ResultStatus.Error(error))
    }

}
