package com.example.testtask.data.datasource.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("65gb/static/raw/maste3r/testTask.json")
    fun loadData(): Deferred<ResponseResult>
}