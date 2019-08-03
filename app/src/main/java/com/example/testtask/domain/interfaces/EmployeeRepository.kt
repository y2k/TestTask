package com.example.testtask.domain.interfaces

import com.example.sdk.other.Either
import com.example.testtask.domain.model.Employee
import com.example.testtask.failure.Failure

interface EmployeeRepository {

    suspend fun getEmployees():Either<Failure,List<Employee>>

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}