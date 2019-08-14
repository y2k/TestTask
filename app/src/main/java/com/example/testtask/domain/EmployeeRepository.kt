package com.example.testtask.domain

import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.domain.model.Employee

interface EmployeeRepository {

    @Deprecated("")
    var selectedEmployee_: Employee?
    @Deprecated("")
    var cachedEmployees: List<Employee>

    @Deprecated("")
    suspend fun getEmployees(): Either<Failure, List<Employee>>

    @Deprecated("")
    fun setSelectedEmployee(employee: Employee)

    @Deprecated("")
    fun getSelectedEmployee(): Employee?

    @Deprecated("")
    suspend fun loadEmployees(): Either<Failure, List<Employee>>

    @Deprecated("")
    suspend fun cacheEmployees(employees: List<Employee>)

    @Deprecated("")
    suspend fun saveEmployeesToDB(employees: List<Employee>)

    @Deprecated("")
    suspend fun getEmployeesFromDB(): List<Employee>
}