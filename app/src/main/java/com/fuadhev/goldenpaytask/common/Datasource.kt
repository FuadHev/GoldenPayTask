package com.fuadhev.goldenpaytask.common

import com.fuadhev.goldenpaytask.common.either.Either


interface GetDatasource<Q, T, E> {
    suspend fun get(query: Q): Either<E, T>
}

/**
 * It is common to persist specific type of data and receive different type of data as return.
 * That is why [RequestType] and [ReturnType] are specified
 */
interface PutDatasource<Q, RequestType, ReturnType, E> {
    suspend fun put(query: Q, data: RequestType): Either<E, ReturnType>
}

/**
 * It is common to request deletion and receive specific type of data as return.
 * That is [T] is specified
 */
interface DeleteDatasource<Q, T, E> {
    suspend fun delete(query: Q): Either<E, T>
}
