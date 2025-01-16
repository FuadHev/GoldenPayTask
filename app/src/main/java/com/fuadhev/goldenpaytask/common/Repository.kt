package com.fuadhev.goldenpaytask.common

import com.fuadhev.goldenpaytask.common.either.Either


interface PutRepository<Q, I, O, E> {
    suspend fun put(query: Q, operation: Operation, data: I): Either<E, O>
}

interface GetRepository<Q, T, E> {
    suspend fun get(query: Q, operation: Operation): Either<E, T>
}

interface DeleteRepository<Q, T, E> {
    suspend fun delete(query: Q, operation: Operation): Either<E, T>
}
