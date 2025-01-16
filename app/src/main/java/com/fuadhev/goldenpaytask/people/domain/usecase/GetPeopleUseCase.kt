package com.fuadhev.goldenpaytask.people.domain.usecase

import com.fuadhev.goldenpaytask.common.DataException
import com.fuadhev.goldenpaytask.common.Operation
import com.fuadhev.goldenpaytask.common.either.Either
import com.fuadhev.goldenpaytask.people.data.repository.PeopleRepository
import com.fuadhev.goldenpaytask.people.domain.model.PeopleDomainModel
import com.fuadhev.goldenpaytask.people.domain.query.PeopleQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetPeopleUseCase @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: PeopleRepository
) {

    suspend operator fun invoke(query : PeopleQuery) :Either<DataException.DataNotFoundException,List<PeopleDomainModel>> {
        return withContext(coroutineDispatcher){
            repository.get(query, Operation.Main)
        }
    }
}