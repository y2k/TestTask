package com.example.testtask.view.viewmodel.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.domain.interactor.employee.EmployeeInteractorImpl
import com.example.testtask.domain.interactor.speciality.SpecialityInteractorImpl
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var employeeInteractor: EmployeeInteractorImpl,
    private var specialityInteractor: SpecialityInteractorImpl
) : ViewModel() {

    val employeeList = MutableLiveData<ArrayList<EmployeeNetwork>>()
    val specialtyList = MutableLiveData<ArrayList<SpecialtyNetwork>>()
    val selectedEmployee = MutableLiveData<EmployeeNetwork>()

    suspend fun init() {
        specialtyList.value = specialityInteractor.getSpecialities()
        employeeList.value = employeeInteractor.getEmployees()
        selectedEmployee.value = employeeInteractor.getSelectedEmployee()
    }

    fun setSelectedEmployee(employeeNetwork: EmployeeNetwork){
        selectedEmployee.value = employeeNetwork
        employeeInteractor.setSelectedEmployee(employeeNetwork)
    }
}