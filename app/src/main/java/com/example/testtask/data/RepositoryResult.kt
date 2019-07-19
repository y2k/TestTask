package com.example.testtask.data

import com.example.testtask.domain.model.Employee

sealed class RepositoryResult {
    data class Error(val errorMessage: String) : RepositoryResult()

    data class Data(val items: List<Employee>) : RepositoryResult()
}