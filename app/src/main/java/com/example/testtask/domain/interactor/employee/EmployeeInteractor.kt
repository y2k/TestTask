package com.example.testtask.domain.interactor.employee

import com.example.testtask.domain.model.Employee

interface EmployeeInteractor {

    suspend fun getEmployees(): List<Employee>

    fun getSelectedEmployee(): Employee?

    fun setSelectedEmployee(employeeNetwork: Employee)
}