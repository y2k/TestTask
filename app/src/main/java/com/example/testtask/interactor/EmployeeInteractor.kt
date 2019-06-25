package com.example.testtask.interactor

import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeInteractor @Inject constructor(private val employeeRepository: EmployeeRepository) {

    suspend fun getData(): ResponseResult {
        return employeeRepository.loadEmployees().await()
    }

    fun cacheEmpoyeesToRepository(responseResult: ResponseResult){
        employeeRepository.cacheEmployees(responseResult.items as ArrayList<Employee>)
    }
}