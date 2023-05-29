package com.fgarcia.list.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fgarcia.list.domain.model.ItemStar
import com.fgarcia.list.domain.usecase.GetListStartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class ListStarsViewModel @Inject constructor(
    private val getListStartUseCase: GetListStartUseCase
) : ViewModel() {

    fun listStarsPagingData(): Flow<PagingData<ItemStar>> {
        return getListStartUseCase(
            GetListStartUseCase.GetListStarParams(getPageConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = PAGE_SIZE
    )

    private companion object {
        const val PAGE_SIZE = 30
    }

}
