package com.example.testtask.repository

import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.network.GitlabApiService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val apiService: GitlabApiService) {
    private lateinit var selectedEmployee: Employee
    private lateinit var employeeList: ArrayList<Employee>

    fun loadEmployees(): Deferred<ResponseResult> {
        return apiService.loadData()
    }

    fun cacheEmployees(employees: ArrayList<Employee>) {
        this.employeeList = employees
    }

    fun getCachedEmployees() = employeeList
}