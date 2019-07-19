package com.example.testtask.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sdk.other.Either
import com.example.testtask.domain.interactor.EmployeeInteractorImpl
import com.example.testtask.domain.interactor.SpecialityInteractorImpl
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var employeeInteractor: EmployeeInteractorImpl,
    private var specialityInteractor: SpecialityInteractorImpl
) : ViewModel() {

    val employeeList = MutableLiveData<ArrayList<Employee>>()
    val specialtyList = MutableLiveData<ArrayList<Speciality>>()
    val selectedEmployee = MutableLiveData<Employee>()

    suspend fun init() {
        val employeeListResult = employeeInteractor.getEmployees()
        when (employeeListResult) {
            is Either.Data -> {
                employeeList.value = employeeListResult.data as ArrayList<Employee>
                specialtyList.value = specialityInteractor.getSpecialities()
                selectedEmployee.value = employeeInteractor.getSelectedEmployee()
            }
        }
    }

    fun setSelectedEmployee(employee: Employee) {
        selectedEmployee.value = employee
        employeeInteractor.setSelectedEmployee(employee)
    }
}