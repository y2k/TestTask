package com.example.testtask.di.module

import com.example.testtask.Config.Companion.BASE_URL
import com.example.testtask.data.datasource.network.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val client = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val api = client.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = api
}