package com.fgarcia.list.domain.usecase

import com.fgarcia.list.data.repository.AboutRepository
import com.fgarcia.list.domain.model.Author
import com.fgarcia.common.arch.coroutines.CoroutinesDispatchers
import com.fgarcia.common.arch.usecase.base.ResultStatus
import com.fgarcia.common.arch.usecase.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface GetAuthorUseCase {
    operator fun invoke(params: GetAuthorParams): Flow<ResultStatus<Author>>
    data class GetAuthorParams(val login: String)
}

class GetAuthorUseCaseImpl @Inject constructor(
    private val repository: AboutRepository,
    private val dispatchers: CoroutinesDispatchers
) : GetAuthorUseCase,
    UseCase<GetAuthorUseCase.GetAuthorParams, Author>(){

    override suspend fun doWork(params: GetAuthorUseCase.GetAuthorParams): ResultStatus<Author> {
        return withContext(dispatchers.io()) {
            ResultStatus.Success(repository.getDataAuthor(params.login))
        }
    }

}
