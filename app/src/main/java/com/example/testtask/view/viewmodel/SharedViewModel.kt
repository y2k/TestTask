package com.example.testtask.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdk.core.network.NetworkHelper
import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.sdk.other.SingleLiveEvent
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import com.example.testtask.view.EmployeeInteractor
import com.example.testtask.view.SpecialityInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var networkChecker: NetworkHelper,
    private var employeeInteractor: EmployeeInteractor,
    private var specialityInteractor: SpecialityInteractor) : ViewModel() {

    val employeeListLiveData = MutableLiveData<ArrayList<Employee>>()
    val specialtyListLiveData = MutableLiveData<ArrayList<Speciality>>()
    val selectedEmployeeLiveData = MutableLiveData<Employee>()

    val progressBarLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Failure>()

    val networkStatusLiveData = SingleLiveEvent<Boolean>()

    init {
        networkStatusLiveData.value = networkChecker.isConnectedToNetwork()
    }

    fun setData() {
        progressBarLiveData.value = true

        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val employeesListResult = employeeInteractor.getEmployees()
                withContext(Dispatchers.Main) {
                    progressBarLiveData.value = false
                    when (employeesListResult) {
                        is Either.Data -> {
                            onDataReady(employeesListResult.data)
                        }
                        is Either.Error -> {
                            errorLiveData.value = employeesListResult.error
                        }
                    }
                }
            }
        }
    }

    private fun onDataReady(employeeList: List<Employee>?) {
        employeeListLiveData.value = employeeList as ArrayList<Employee>
        specialtyListLiveData.value = specialityInteractor.getSpecialities()
        selectedEmployeeLiveData.value = employeeInteractor.getSelectedEmployee()
    }

    fun setSelectedEmployee(employee: Employee) {
        selectedEmployeeLiveData.value = employee
        employeeInteractor.setSelectedEmployee(employee)
    }
}