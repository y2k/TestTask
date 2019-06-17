package com.example.testtask.contracts

import com.example.testtask.model.ResponseResult

interface MainActivityContract {
    fun setLoading(state:Boolean)

    fun onDataReady(result: ResponseResult)
}