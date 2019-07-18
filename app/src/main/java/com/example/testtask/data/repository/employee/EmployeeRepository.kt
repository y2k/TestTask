package com.example.testtask.data.repository.employee

import com.example.testtask.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():List<Employee>

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}