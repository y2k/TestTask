package com.example.testtask.view

import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.domain.model.Employee

interface EmployeeInteractor {

    fun setOfflineMode(isOfflineMode:Boolean)

    suspend fun getEmployees(): Either<Failure,List<Employee>>

    fun getSelectedEmployee(): Employee?

    fun setSelectedEmployee(employeeNetwork: Employee)
}