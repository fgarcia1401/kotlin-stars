package com.fgarcia.kotlinstars.presentation.list.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fgarcia.common.arch.usecase.base.UseCase
import com.fgarcia.kotlinstars.data.repository.GitRepository
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.presentation.list.usecase.GetListStartUseCase.GetListStarParams
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetListStartUseCase {
    operator fun invoke(params: GetListStarParams): Flow<PagingData<ItemStar>>
    data class GetListStarParams(val pagingConfig: PagingConfig)
}

class GetListStartUseCaseImpl @Inject constructor(
    private val repository: GitRepository
) : UseCase.PagingUseCase<GetListStarParams, ItemStar>(),
    GetListStartUseCase {

    override fun createFlowObservable(params: GetListStarParams): Flow<PagingData<ItemStar>> {
        return repository.getCachedListStarsWithMostStars(params.pagingConfig)
    }

}