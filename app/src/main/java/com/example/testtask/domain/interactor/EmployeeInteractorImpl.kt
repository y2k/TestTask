package com.example.testtask.domain.interactor

import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.domain.EmployeeRepository
import com.example.testtask.domain.SpecialityRepository
import com.example.testtask.domain.model.Employee
import com.example.testtask.view.EmployeeInteractor
import timber.log.Timber
import javax.inject.Inject

class EmployeeInteractorImpl @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val specialityRepository: SpecialityRepository
) : EmployeeInteractor {

    private var isOfflineMode:Boolean = false

    override fun setOfflineMode(isOfflineMode: Boolean) {
        this.isOfflineMode = isOfflineMode
    }

    override suspend fun getEmployees(): Either<Failure, List<Employee>> {
        employeeRepository.setOfflineMode(isOfflineMode)
        Timber.e("HERE????")
        val employeeListResult = employeeRepository.getEmployees()
        when (employeeListResult) {
            is Either.Data -> {
                if (specialityRepository.getSpecialities().isEmpty()) {
                    specialityRepository.setSpecialitiesFromEmployeeList(employeeListResult.data)
                }
                return employeeListResult
            }
            else -> return employeeListResult
        }
    }

    override fun getSelectedEmployee(): Employee? {
        return employeeRepository.getSelectedEmployee()
    }

    override fun setSelectedEmployee(employee: Employee) {
        employeeRepository.setSelectedEmployee(employee)
    }
}