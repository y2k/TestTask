package com.example.testtask.data.repository.employee

import com.example.testtask.data.model.RepositoryResult
import com.example.testtask.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():RepositoryResult

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}