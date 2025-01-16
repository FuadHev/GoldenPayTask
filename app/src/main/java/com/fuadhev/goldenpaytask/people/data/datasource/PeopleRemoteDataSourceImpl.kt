package com.fuadhev.goldenpaytask.people.data.datasource

import com.fuadhev.goldenpaytask.common.DataException
import com.fuadhev.goldenpaytask.common.either.Either
import com.fuadhev.goldenpaytask.people.data.api.WebApiService
import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteModel
import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteResponse
import com.fuadhev.goldenpaytask.people.domain.query.PeopleQuery
import javax.inject.Inject

class PeopleRemoteDataSourceImpl @Inject constructor(
    private val apiManager: WebApiService
) : PeopleRemoteDataSource {

    override suspend fun get(query: PeopleQuery): Either<DataException.DataNotFoundException, List<PeopleRemoteModel>> {
        when (query) {
            is PeopleQuery.GetPeopleQuery -> {
                val response = apiManager.getPeoples()

                if (!response.isSuccessful) {
                    return Either.Left(DataException.DataNotFoundException())
                }

                val responseObj : PeopleRemoteResponse = response.body() as PeopleRemoteResponse

                return Either.Right(responseObj.results)

            }
        }
    }
}