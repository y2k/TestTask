package com.example.testtask.data.repository

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.testtask.data.RepositoryResult
import com.example.testtask.data.toDBModel
import com.example.testtask.data.network.GitlabApiService
import com.example.testtask.data.network.ResponseResult
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.repository.EmployeeRepository
import com.example.testtask.data.toDomian
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
        private val apiService: GitlabApiService,
        private val dbHelper: DBHelper
) : EmployeeRepository {

    private var selectedEmployee: Employee? = null
    private var cachedEmployees = listOf<Employee>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    override suspend fun getEmployees(): RepositoryResult {
        if (cachedEmployees.isNotEmpty()) {
            return RepositoryResult.Data(cachedEmployees)
        } else {
            return when (val loadedEmployeesResult = loadEmployees()) {
                is RepositoryResult.Data -> {
                    cacheEmployees(loadedEmployeesResult.items)
                    saveEmployeesToDB(loadedEmployeesResult.items)
                    loadedEmployeesResult
                }
                is RepositoryResult.Error -> {
                    loadedEmployeesResult
                }
            }
        }
    }

    override fun setSelectedEmployee(employee: Employee) {
        selectedEmployee = employee
    }

    override fun getSelectedEmployee(): Employee? {
        return selectedEmployee
    }

    private suspend fun loadEmployees(): RepositoryResult {
        try {
            val employeeList = apiService.loadData().await().items.map { it.toDomian() }
            return RepositoryResult.Data(employeeList)
        } catch (e: HttpException) {
            Timber.e("HttpException cathed: ${e.code()}")
            return RepositoryResult.Error(e.code().toString())
        }
    }

    private fun cacheEmployees(employees: List<Employee>) {
        this.cachedEmployees = employees
    }

    private fun saveEmployeesToDB(employees: List<Employee>) {
        val convertedEmployees = employees.map { it.toDBModel() }
        dbHelper.writeEmployeesToDB(convertedEmployees as ArrayList<EmployeeDB>)
    }
}