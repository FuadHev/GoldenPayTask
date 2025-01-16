package com.fuadhev.goldenpaytask.people.data.repository

import com.fuadhev.goldenpaytask.common.DataException
import com.fuadhev.goldenpaytask.common.Operation
import com.fuadhev.goldenpaytask.common.either.Either
import com.fuadhev.goldenpaytask.people.data.datasource.PeopleRemoteDataSource
import com.fuadhev.goldenpaytask.people.data.mapper.toDomainModelList
import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteModel
import com.fuadhev.goldenpaytask.people.domain.model.PeopleDomainModel
import com.fuadhev.goldenpaytask.people.domain.query.PeopleQuery
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val remoteDataSource: PeopleRemoteDataSource) :
    PeopleRepository {

    override suspend fun get(
        query: PeopleQuery,
        operation: Operation
    ): Either<DataException.DataNotFoundException, List<PeopleDomainModel>> {
        return when (operation) {
            is Operation.Main -> {
                remoteDataSource.get(query).map { remoteList ->
                    remoteList.toDomainModelList()
                }
            }

            is Operation.Cache -> {
                return Either.Left(DataException.DataNotFoundException())
            }

            is Operation.MainThenCache -> {
                return Either.Left(DataException.DataNotFoundException())
            }

            is Operation.CacheThenMain -> {
                return Either.Left(DataException.DataNotFoundException())
            }
        }
    }
}