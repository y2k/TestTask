package com.example.testtask.view.viewmodel.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.interactor.EmployeeInteractor
import com.example.testtask.domain.interactor.SpecialityInteractor
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Specialty
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var employeeInteractor: EmployeeInteractor,
    private var specialityInteractor: SpecialityInteractor) : ViewModel() {

    val employeeList = MutableLiveData<ArrayList<Employee>>()
    val specialtyList = MutableLiveData<ArrayList<Specialty>>()
    val selectedEmployee = MutableLiveData<Employee>()

    suspend fun init() {
        specialtyList.value = specialityInteractor.getSpecialities()
        employeeList.value = employeeInteractor.getEmployees()
        selectedEmployee.value = employeeInteractor.getSelectedEmployee()
    }

    fun setSelectedEmployee(employee: Employee){
        selectedEmployee.value = employee
        employeeInteractor.setSelectedEmployee(employee)
    }
}