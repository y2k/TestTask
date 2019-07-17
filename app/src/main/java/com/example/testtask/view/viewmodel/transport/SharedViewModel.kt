package com.example.testtask.view.viewmodel.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.interactor.employee.EmployeeInteractorImpl
import com.example.testtask.domain.interactor.speciality.SpecialityInteractorImpl
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Specialty
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var employeeInteractor: EmployeeInteractorImpl,
    private var specialityInteractor: SpecialityInteractorImpl
) : ViewModel() {

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