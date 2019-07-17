package com.example.testtask.data.repository.employee

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.testtask.data.model.RepositoryResult
import com.example.testtask.domain.toDBModel
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.ResponseResult
import com.example.testtask.data.network.GitlabApiService
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: GitlabApiService,
    private val dbHelper: DBHelper
) : EmployeeRepository {

    private var selectedEmployee: Employee? = null
    private var cachedEmployees = arrayListOf<Employee>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    override suspend fun getEmployees(): RepositoryResult {
        if (cachedEmployees.isNotEmpty()) {
            return RepositoryResult.ResponseResult(cachedEmployees)
        } else {
            val loadedEmployeesResult = loadEmployees()
            when (loadedEmployeesResult){
                is RepositoryResult.Error ->{}
                is RepositoryResult.ResponseResult ->{
                    cacheEmployees(loadedEmployeesResult.items)
                    saveEmployeesToDB(loadedEmployeesResult.items)
                    return loadedEmployeesResult
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
            return RepositoryResult.ResponseResult(apiService.loadData().await().items)
        } catch (e: HttpException) {
            Timber.e("HttpException: " + e.code())
            return RepositoryResult.Error(e.code().toString())
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