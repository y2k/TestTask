package com.example.testtask.domain.interactor.employee

import com.example.testtask.data.RepositoryResult
import com.example.testtask.domain.repository.EmployeeRepository
import com.example.testtask.domain.repository.SpecialityRepository
import com.example.testtask.domain.model.Employee
import javax.inject.Inject

class EmployeeInteractorImpl @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val specialityRepository: SpecialityRepository
) : EmployeeInteractor {

    override suspend fun getEmployees(): EmployeeInteractorResult {
        val employeeListResult = employeeRepository.getEmployees()

        when (employeeListResult) {
            is RepositoryResult.Data -> {
                if (specialityRepository.getSpecialities().isEmpty()) {
                    specialityRepository.setSpecialitiesFromEmployeeList(employeeListResult.items)
                }
                val list = employeeListResult.items
                return EmployeeInteractorResult.Data(list)
            }
            is RepositoryResult.Error -> {
                return EmployeeInteractorResult.Error(employeeListResult.errorMessage)
            }
        }
    }

    override fun getSelectedEmployee(): Employee? {
        return employeeRepository.getSelectedEmployee()
    }

    override fun setSelectedEmployee(employee: Employee) {
        employeeRepository.setSelectedEmployee(employee)
    }
}