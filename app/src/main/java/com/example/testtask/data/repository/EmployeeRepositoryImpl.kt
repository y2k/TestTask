package com.example.testtask.data.repository

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.sdk.extensions.fixBirthday
import com.example.sdk.extensions.fixName
import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.data.toDBModel
import com.example.testtask.data.network.GitlabApiService
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.EmployeeRepository
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
    override suspend fun getEmployees(): Either<Failure, List<Employee>> {
        if (cachedEmployees.isNotEmpty()) {
            return Either.Data(cachedEmployees)
        } else {
            return when (val loadedEmployeesResult = loadEmployees()) {
                is Either.Data -> {
                    cacheEmployees(loadedEmployeesResult.data)
                    saveEmployeesToDB(loadedEmployeesResult.data)
                    loadedEmployeesResult
                }
                is Either.Error -> {
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

    private suspend fun loadEmployees(): Either<Failure, List<Employee>> {
        try {
            val employeeList = apiService.loadData().await().items.map { it.toDomian() }

            employeeList.forEach { employee ->
                employee.firstName = employee.firstName?.fixName()
                employee.lastName = employee.lastName?.fixName()
                employee.birthday = employee.birthday?.fixBirthday()
            }

            return Either.Data(employeeList)
        } catch (e: HttpException) {
            Timber.e("HttpException cathed: ${e.code()}")
            return Either.Error(Failure(e.code().toString(), "Http Error"))
        }
    }

    private fun cacheEmployees(employees: List<Employee>) {
        this.cachedEmployees = employees
    }

    private fun saveEmployeesToDB(employees: List<Employee>) {
        val convertedEmployees = ArrayList<EmployeeDB>()
        for (i in 0 until employees.size) {
            convertedEmployees.add(employees[i].toDBModel(i))
        }
        dbHelper.writeEmployeesToDB(convertedEmployees)
    }
}