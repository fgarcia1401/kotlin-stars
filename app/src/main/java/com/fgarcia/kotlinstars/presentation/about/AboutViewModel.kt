package com.fgarcia.kotlinstars.presentation.about

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.fgarcia.common.arch.usecase.base.ResultStatus
import com.fgarcia.common.arch.usecase.base.ResultStatus.Loading
import com.fgarcia.kotlinstars.domain.model.Author
import com.fgarcia.kotlinstars.presentation.about.usecase.GetAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val getAuthorUseCase: GetAuthorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ResultStatus<Author>>(Loading)
    val uiState : StateFlow<ResultStatus<Author>> = _uiState

    fun getDataAuthor(login: String = LOGIN_DEFAULT) {
        viewModelScope.launch {
            getAuthorUseCase(GetAuthorUseCase.GetAuthorParams(login)).collect { author ->
                _uiState.value = author
            }
        }
    }

    private companion object {
        const val LOGIN_DEFAULT = "fgarcia1401"
    }
}