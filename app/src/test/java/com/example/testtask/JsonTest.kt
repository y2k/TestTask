package com.example.testtask

import com.example.testtask.Config.Companion.BASE_URL
import com.example.testtask.network.GitlabApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonTest {

    @Test
    fun isJsonCorrect() = runBlocking {

        val client = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val api = client.create(GitlabApiService::class.java)
        Assert.assertNotNull("API", api)

        val responseResult = api.loadData()
        Assert.assertNotNull("Response", responseResult)

        val data = responseResult.await().items
        Assert.assertNotNull("ResponseData", data)
    }
}