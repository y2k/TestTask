package com.example.testtask.data.repository.employee

import com.example.testtask.data.model.RepositoryResult
import com.example.testtask.data.model.EmployeeNetwork

interface EmployeeRepository {
    suspend fun getEmployees():RepositoryResult

    fun setSelectedEmployee(employeeNetwork: EmployeeNetwork)

    fun getSelectedEmployee(): EmployeeNetwork?
}