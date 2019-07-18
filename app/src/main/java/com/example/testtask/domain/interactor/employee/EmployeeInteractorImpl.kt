package com.example.testtask.domain.interactor.employee

import com.example.testtask.data.repository.employee.EmployeeRepository
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.repository.speciality.SpecialityRepository
import javax.inject.Inject

class EmployeeInteractorImpl @Inject constructor(private val employeeRepository: EmployeeRepository,
                                                 private val specialityRepository: SpecialityRepository):EmployeeInteractor {

    override suspend fun getEmployees(): ArrayList<EmployeeNetwork> {
        val employeeList = employeeRepository.getEmployees()

        if (specialityRepository.getSpecialities().isEmpty()) {
            when(employeeList){
//                is ResponseResult. ->{}
            }
            specialityRepository.setSpecialitiesFromEmployeeList(ArrayList())
        }

        return ArrayList()
    }

    override fun getSelectedEmployee():EmployeeNetwork?{
        return employeeRepository.getSelectedEmployee()
    }

    override fun setSelectedEmployee(employeeNetwork: EmployeeNetwork){
        employeeRepository.setSelectedEmployee(employeeNetwork)
    }
}