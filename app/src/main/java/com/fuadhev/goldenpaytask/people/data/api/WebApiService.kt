package com.fuadhev.goldenpaytask.people.data.api

import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebApiService {

    @GET("people")
    suspend fun getPeoples():Response<PeopleRemoteResponse>

}