package com.fgarcia.list.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fgarcia.common.arch.usecase.base.UseCase
import com.fgarcia.list.data.repository.ListRepository
import com.fgarcia.list.domain.model.ItemStar
import com.fgarcia.list.domain.usecase.GetListStartUseCase.GetListStarParams
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetListStartUseCase {
    operator fun invoke(params: GetListStarParams): Flow<PagingData<ItemStar>>
    data class GetListStarParams(val pagingConfig: PagingConfig)
}

class GetListStartUseCaseImpl @Inject constructor(
    private val repository: ListRepository
) : UseCase.PagingUseCase<GetListStarParams, ItemStar>(),
    GetListStartUseCase {

    override fun createFlowObservable(params: GetListStarParams): Flow<PagingData<ItemStar>> {
        val pagingSource = repository.getListStarsWithMostStars()
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }

}
