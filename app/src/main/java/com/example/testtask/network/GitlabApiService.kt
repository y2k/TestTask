package com.example.testtask.network

import com.example.testtask.model.ResponseResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GitlabApiService {

    //I think it's normal to hardcode the only one remote
    @GET("65gb/static/raw/master/testTask.json")
    fun loadData(): Deferred<ResponseResult>
}