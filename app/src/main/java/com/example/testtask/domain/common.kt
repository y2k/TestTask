package com.example.testtask.domain

import androidx.lifecycle.MutableLiveData
import com.example.sdk.core.network.NetworkHelper
import com.example.sdk.other.Either
import com.example.testtask.data.datasource.network.ApiService
import com.example.testtask.data.datasource.network.ResponseResult
import retrofit2.HttpException
import timber.log.Timber

@Deprecated("Side effect")
fun <T> MutableLiveData<T>.update(f: (T) -> T) {
    value = f(value!!)
}

object Services {

    lateinit var api: ApiServiceWrapper
    lateinit var networkHelper: NetworkHelper

    class ApiServiceWrapper(private val apiService: ApiService) {

        suspend fun loadData(): Either<HttpException, ResponseResult> =
            try {
                Either.Data(apiService.loadData().await())
            } catch (e: HttpException) {
                Timber.e("HttpException cathed: ${e.code()}")
                Either.Error(e)
            }
    }
}
