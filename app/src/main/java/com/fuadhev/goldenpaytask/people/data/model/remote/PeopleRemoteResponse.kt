package com.fuadhev.goldenpaytask.people.data.model.remote


data class PeopleRemoteResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PeopleRemoteModel>
)