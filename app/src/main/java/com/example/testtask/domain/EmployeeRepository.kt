package com.example.testtask.domain

import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.domain.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees():Either<Failure,List<Employee>>

    fun setSelectedEmployee(employee: Employee)

    fun getSelectedEmployee(): Employee?
}