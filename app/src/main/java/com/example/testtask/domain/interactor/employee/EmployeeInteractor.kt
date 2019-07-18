package com.example.testtask.domain.interactor.employee

import com.example.testtask.data.model.EmployeeNetwork

interface EmployeeInteractor {

    suspend fun getEmployees(): ArrayList<EmployeeNetwork>

    fun getSelectedEmployee(): EmployeeNetwork?

    fun setSelectedEmployee(employeeNetwork: EmployeeNetwork)
}