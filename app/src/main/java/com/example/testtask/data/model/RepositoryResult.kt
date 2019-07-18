package com.example.testtask.data.model

sealed class RepositoryResult {
    data class Error(val errorMessage: String) : RepositoryResult()

    data class ResponseResult(val items: List<EmployeeNetwork>) : RepositoryResult()
}