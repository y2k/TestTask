package com.example.testtask.data.repository

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.testtask.data.toDBModel
import com.example.testtask.data.network.GitlabApiService
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.repository.EmployeeRepository
import com.example.testtask.data.toDomian
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: GitlabApiService,
    private val dbHelper: DBHelper
) : EmployeeRepository {

    private var selectedEmployee: Employee? = null
    private var cachedEmployees = listOf<Employee>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    override suspend fun getEmployees(): List<Employee> {
        if (cachedEmployees.isNotEmpty()) {
            return cachedEmployees
        } else {
            val loadedEmployeesResult = loadEmployees()
            cacheEmployees(loadedEmployeesResult)
            saveEmployeesToDB(loadedEmployeesResult)
            return loadedEmployeesResult
        }
    }

    override fun setSelectedEmployee(employee: Employee) {
        selectedEmployee = employee
    }

    override fun getSelectedEmployee(): Employee? {
        return selectedEmployee
    }

    private suspend fun loadEmployees(): List<Employee> {
        return apiService.loadData().await().items.map { it.toDomian() }
    }

    private fun cacheEmployees(employees: List<Employee>) {
        this.cachedEmployees = employees
    }

    private fun saveEmployeesToDB(employees: List<Employee>) {
        val convertedEmployees = employees.map { it.toDBModel() }
        dbHelper.writeEmployeesToDB(convertedEmployees as ArrayList<EmployeeDB>)
    }
}