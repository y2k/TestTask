package com.example.testtask.domain.repository

import com.example.testtask.data.RepositoryResult
import com.example.testtask.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():RepositoryResult

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}