package com.example.testtask.data.repository.employee

import com.example.testtask.data.model.EmployeeNetwork

sealed class RepositoryResult {
    data class Error(val errorMessage: String) : RepositoryResult()

    data class NetworkResult(val items: List<EmployeeNetwork>) : RepositoryResult()

    data class DataBaseResult(val items: List<EmployeeNetwork>) : RepositoryResult()

    data class CachedResult(val items: List<EmployeeNetwork>) : RepositoryResult()
}