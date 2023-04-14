package com.fgarcia.kotlinstars.presentation.list.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fgarcia.core.usecase.base.UseCase
import com.fgarcia.kotlinstars.data.repository.StarRepository
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.presentation.list.usecase.GetListStartUseCase.GetListStarParams
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetListStartUseCase @Inject constructor(
    private val repository: StarRepository
) : UseCase.PagingUseCase<GetListStarParams, ItemStar>() {

    data class GetListStarParams(val pagingConfig: PagingConfig)

    override fun createFlowObservable(params: GetListStarParams): Flow<PagingData<ItemStar>> {
        return Pager(config = params.pagingConfig) {
            repository.getListStarsWithMostStars()
        }.flow
    }

}