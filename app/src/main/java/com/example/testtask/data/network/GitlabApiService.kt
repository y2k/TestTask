package com.example.testtask.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GitlabApiService {

    @GET("65gb/static/raw/master/te1stTask.json")
    fun loadData(): Deferred<ResponseResult>
}