package com.fuadhev.goldenpaytask.common

sealed class DataException(override val cause: Throwable? = null) : Throwable(cause) {
    data class DataNotFoundException(override val cause: Throwable? = null) : DataException(cause)
}
