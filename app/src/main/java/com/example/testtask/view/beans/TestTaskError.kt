package com.example.testtask.view.beans

data class TestTaskError(val errorType:ErrorType, val message:String)

enum class ErrorType {
    DATABASE,
    NETWORK,
    OTHER
}