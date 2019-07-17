package com.example.testtask.domain.interactor.employee

import com.example.testtask.domain.model.Employee

interface EmployeeInteractor {

    suspend fun getEmployees(): ArrayList<Employee>

    fun getSelectedEmployee(): Employee?

    fun setSelectedEmployee(employee: Employee)
}