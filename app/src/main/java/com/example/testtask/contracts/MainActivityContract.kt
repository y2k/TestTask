package com.example.testtask.contracts

interface MainActivityContract{
    fun setLoading(state:Boolean)

    suspend fun onDataReady()
}