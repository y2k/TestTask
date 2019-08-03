package com.example.testtask.domain.interfaces

import com.example.sdk.other.Either
import com.example.testtask.domain.model.Employee
import com.example.testtask.failure.Failure

interface EmployeeInteractor {

    suspend fun getEmployees(): Either<Failure,List<Employee>>

    fun getSelectedEmployee(): Employee?

    fun setSelectedEmployee(employeeNetwork: Employee)
}