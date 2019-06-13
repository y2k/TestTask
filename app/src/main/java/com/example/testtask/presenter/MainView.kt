package com.example.testtask.presenter

import com.example.testtask.model.ResponseResult

interface MainView {
    fun setLoading(state:Boolean)

    fun onDataReady(result: ResponseResult)
}