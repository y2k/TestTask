package com.example.testtask.data.model

import com.example.testtask.domain.model.Employee

sealed class RepositoryResult {
    data class Error(val errorMessage: String) : RepositoryResult()

    data class ResponseResult(val items: List<Employee>) : RepositoryResult()
}