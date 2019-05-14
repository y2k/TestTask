package com.example.testtask.presenter

import com.example.testtask.model.ResponseResult

interface MainView {
    fun onDataReady(result: ResponseResult)
}