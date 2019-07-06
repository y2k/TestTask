package com.example.testtask.repository

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.testtask.extensions.mapper.toDBModel
import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.network.GitlabApiService
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiService: GitlabApiService,
    private val dbHelper: DBHelper
) {

    private var selectedEmployee: Employee? = null
    private var cachedEmployees = arrayListOf<Employee>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    suspend fun getEmployees(): ArrayList<Employee> {
        if (cachedEmployees.isNotEmpty()) {
            return cachedEmployees
        } else {
            val loadedEmployees = loadEmployees().items as ArrayList<Employee>
            cacheEmployees(loadedEmployees)
            saveEmployeesToDB(loadedEmployees)
            return loadedEmployees
        }
    }

    fun setSelectedEmployee(employee: Employee) {
        selectedEmployee = employee
    }

    fun getSelectedEmployee(): Employee? {
        return selectedEmployee
    }

    private suspend fun loadEmployees(): ResponseResult {
        return apiService.loadData().await()
    }

    private fun cacheEmployees(employees: ArrayList<Employee>) {
        this.cachedEmployees = employees
    }

    private fun saveEmployeesToDB(employees: ArrayList<Employee>) {
        val convertedEmployees = employees.map { it.toDBModel() }
        dbHelper.writeEmployeesToDB(convertedEmployees as ArrayList<EmployeeDB>)
    }
}