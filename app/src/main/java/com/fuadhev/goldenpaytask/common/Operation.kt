package com.fuadhev.goldenpaytask.common

sealed class Operation {

    // Operate on main datasource only.
    object Main : Operation()

    // Operate on cache datasource only.
    object Cache : Operation()

    // Operate on cache datasource then on main datasource.
    object MainThenCache : Operation()

    // Operate on main datasource then on cache datasource.
    object CacheThenMain : Operation()
}
