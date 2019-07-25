package com.example.testtask.data.datasource.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GitlabApiService {

    @GET("65gb/static/raw/mas2ter/testTask.json")
    fun loadData(): Deferred<ResponseResult>
}