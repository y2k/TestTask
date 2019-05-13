package com.example.testtask.network

import com.example.testtask.model.ResponseResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GitlabApiService {

    @GET("65gb/static/raw/master/testTask.json")
    fun loadData(): Observable<ResponseResult>

    companion object Factory {
        fun create(): GitlabApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gitlab.65apps.com/")
                .build()

            return retrofit.create(GitlabApiService::class.java)
        }
    }
}