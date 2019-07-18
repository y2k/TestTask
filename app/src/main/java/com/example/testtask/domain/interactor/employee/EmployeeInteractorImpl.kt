package com.example.testtask.domain.interactor.employee

import com.example.testtask.data.repository.employee.EmployeeRepository
import com.example.testtask.data.repository.speciality.SpecialityRepository
import com.example.testtask.domain.model.Employee
import javax.inject.Inject

class EmployeeInteractorImpl @Inject constructor(private val employeeRepository: EmployeeRepository,
                                                 private val specialityRepository: SpecialityRepository):EmployeeInteractor {

    override suspend fun getEmployees(): List<Employee> {
        val employeeList = employeeRepository.getEmployees()

        if (specialityRepository.getSpecialities().isEmpty()) {
            specialityRepository.setSpecialitiesFromEmployeeList(employeeList)
        }

        return employeeList
    }

    override fun getSelectedEmployee():Employee?{
        return employeeRepository.getSelectedEmployee()
    }

    override fun setSelectedEmployee(employee: Employee){
        employeeRepository.setSelectedEmployee(employee)
    }
}