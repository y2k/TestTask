package com.example.testtask.domain.interactor

import com.example.testtask.domain.model.Employee
import com.example.testtask.data.repository.EmployeeRepository
import com.example.testtask.data.repository.SpecialityRepository
import javax.inject.Inject

class EmployeeInteractor @Inject constructor(private val employeeRepository: EmployeeRepository,
                                             private val specialityRepository: SpecialityRepository) {

    suspend fun getEmployees(): ArrayList<Employee> {
        val employeeList = employeeRepository.getEmployees()

        if (specialityRepository.getSpecialities().isEmpty() && employeeList.isNotEmpty()) {
            specialityRepository.setSpecialitiesFromEmployeeList(employeeList)
        }

        return employeeList
    }

    fun getSelectedEmployee():Employee?{
        return employeeRepository.getSelectedEmployee()
    }

    fun setSelectedEmployee(employee: Employee){
        employeeRepository.setSelectedEmployee(employee)
    }
}