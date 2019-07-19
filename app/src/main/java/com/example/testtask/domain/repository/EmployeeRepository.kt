package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():List<Employee>

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}