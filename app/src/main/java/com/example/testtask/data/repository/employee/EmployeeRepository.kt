package com.example.testtask.data.repository.employee

import com.example.testtask.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():ArrayList<Employee>

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}