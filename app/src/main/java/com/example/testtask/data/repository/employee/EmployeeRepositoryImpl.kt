package com.example.testtask.data.repository.employee

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.testtask.domain.toDBModel
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.ResponseResult
import com.example.testtask.data.network.GitlabApiService
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(private val apiService: GitlabApiService,
                                                 private val dbHelper: DBHelper) : EmployeeRepository {

    private var selectedEmployee: Employee? = null
    private var cachedEmployees = arrayListOf<Employee>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    override suspend fun getEmployees(): ArrayList<Employee> {
        if (cachedEmployees.isNotEmpty()) {
            return cachedEmployees
        } else {
            val loadedEmployees = loadEmployees().items as ArrayList<Employee>
            cacheEmployees(loadedEmployees)
            saveEmployeesToDB(loadedEmployees)
            return loadedEmployees
        }
    }

    override fun setSelectedEmployee(employee: Employee) {
        selectedEmployee = employee
    }

    override fun getSelectedEmployee(): Employee? {
        return selectedEmployee
    }

    private suspend fun loadEmployees(): ResponseResult {
        try {
            return apiService.loadData().await()
        } catch (e: HttpException) {
            Timber.e("HttpException: " + e.code())

            val responseResult = ResponseResult(ArrayList())
            responseResult.errorCode = e.code()
            return responseResult
        }
    }

    private fun cacheEmployees(employees: ArrayList<Employee>) {
        this.cachedEmployees = employees
    }

    private fun saveEmployeesToDB(employees: ArrayList<Employee>) {
        val convertedEmployees = employees.map { it.toDBModel() }
        dbHelper.writeEmployeesToDB(convertedEmployees as ArrayList<EmployeeDB>)
    }
}