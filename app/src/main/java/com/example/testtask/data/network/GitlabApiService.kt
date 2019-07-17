package com.example.testtask.data.network

import com.example.testtask.domain.model.ResponseResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GitlabApiService {

    @GET("65gb/static/raw/master/testTa2sk.json")
    fun loadData(): Deferred<ResponseResult>
}