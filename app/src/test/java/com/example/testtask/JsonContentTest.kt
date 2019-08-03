package com.example.testtask

import com.example.testtask.Config.Companion.BASE_URL
import com.example.testtask.data.datasource.network.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonContentTest {

    @Test
    fun isJsonCorrect() = runBlocking {

        val client = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val api = client.create(ApiService::class.java)
        val responseResult = api?.loadData()
        val data = responseResult?.await()?.items

        Assert.assertNotNull("API", api)
        Assert.assertNotNull("Response", responseResult)
        Assert.assertNotNull("ResponseData", data)
        Assert.assertNotNull("ResponseData", data)
    }
}