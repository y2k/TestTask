package com.example.testtask.data.repository.employee

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.testtask.data.model.RepositoryResult
import com.example.testtask.domain.toDBModel
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.network.GitlabApiService
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: GitlabApiService,
    private val dbHelper: DBHelper
) : EmployeeRepository {

    private var selectedEmployeeNetwork: EmployeeNetwork? = null
    private var cachedEmployees = arrayListOf<EmployeeNetwork>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    override suspend fun getEmployees(): RepositoryResult {
        if (cachedEmployees.isNotEmpty()) {
            return RepositoryResult.ResponseResult(cachedEmployees)
        } else {
            val loadedEmployeesResult = loadEmployees()
            when (loadedEmployeesResult) {
                is RepositoryResult.Error -> {
                    return loadedEmployeesResult
                }
                is RepositoryResult.ResponseResult -> {
                    cacheEmployees(loadedEmployeesResult.items as ArrayList<EmployeeNetwork>)
                    saveEmployeesToDB(loadedEmployeesResult.items)
                    return loadedEmployeesResult
                }
            }
        }
    }

    override fun setSelectedEmployee(employeeNetwork: EmployeeNetwork) {
        selectedEmployeeNetwork = employeeNetwork
    }

    override fun getSelectedEmployee(): EmployeeNetwork? {
        return selectedEmployeeNetwork
    }

    private suspend fun loadEmployees(): RepositoryResult {
        try {
            return RepositoryResult.ResponseResult(apiService.loadData().await().items)
        } catch (e: HttpException) {
            Timber.e("HttpException: " + e.code())
            return RepositoryResult.Error(e.code().toString())
        }
    }

    private fun cacheEmployees(employeeNetworks: ArrayList<EmployeeNetwork>) {
        this.cachedEmployees = employeeNetworks
    }

    private fun saveEmployeesToDB(employeeNetworks: ArrayList<EmployeeNetwork>) {
        val convertedEmployees = employeeNetworks.map { it.toDBModel() }
        dbHelper.writeEmployeesToDB(convertedEmployees as ArrayList<EmployeeDB>)
    }
}