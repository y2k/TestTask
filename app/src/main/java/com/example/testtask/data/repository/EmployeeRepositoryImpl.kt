package com.example.testtask.data.repository

import com.example.sdk.core.network.NetworkHelper
import com.example.sdk.extensions.fixBirthday
import com.example.sdk.extensions.fixName
import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.sdk.other.FailureType
import com.example.testtask.data.datasource.network.ApiService
import com.example.testtask.data.toDomain
import com.example.testtask.data.datasource.database.DBHelper
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.EmployeeRepository
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dbHelper: DBHelper,
    private var networkHelper: NetworkHelper) : EmployeeRepository {

    private var selectedEmployee: Employee? = null
    private var cachedEmployees = listOf<Employee>()

    //If there is no employees into cache, we load data from server and cache it/save to DB
    override suspend fun getEmployees(): Either<Failure, List<Employee>> {
        if (cachedEmployees.isNotEmpty()) {
            return Either.Data(cachedEmployees)
        }

        if (networkHelper.isOfflineModeEnabled) {
            val employees = getEmployeesFromDB()

            return when {
                !employees.isNullOrEmpty() -> {
                    cacheEmployees(employees)
                    Either.Data(getEmployeesFromDB())
                }
                else -> Either.Error(Failure(FailureType.DATABASE, "В базе данных нет записей"))
            }
        }

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

    override fun setSelectedEmployee(employee: Employee) {
        selectedEmployee = employee
    }

    override fun getSelectedEmployee(): Employee? {
        return selectedEmployee
    }

    private suspend fun loadEmployees(): Either<Failure, List<Employee>> {
        try {
            val employeeList = apiService.loadData().await()
            val resultList = employeeList.items.map {
                it.toDomain().apply {
                    firstName = this.firstName.fixName()
                    lastName = this.lastName.fixName()
                    birthday = this.birthday.fixBirthday()
                }
            }
            return Either.Data(resultList)
        } catch (e: HttpException) {
            Timber.e("HttpException cathed: ${e.code()}")
            return Either.Error(Failure(FailureType.NETWORK, "Http Error: ${e.code()} code"))
        }
    }

    private fun cacheEmployees(employees: List<Employee>) {
        this.cachedEmployees = employees
    }

    private fun saveEmployeesToDB(employees: List<Employee>) {
        dbHelper.writeEmployeesToDB(employees)
    }

    private fun getEmployeesFromDB(): List<Employee> {
        return dbHelper.readEmployeesFromDB()
    }
}
