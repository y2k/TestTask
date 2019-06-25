package com.example.testtask.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.App
import com.example.testtask.model.Employee
import com.example.testtask.model.Specialty
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import timber.log.Timber
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var specialityRepository: SpecialityRepository,
    private var employeeRepository: EmployeeRepository,
    private var dataBaseRepository: DataBaseRepository
) : ViewModel() {

    val employeeList = MutableLiveData<ArrayList<Employee>>()
    val specialtyList = MutableLiveData<ArrayList<Specialty>>()
    val selectedEmployee = MutableLiveData<Employee>()

    fun init() {
        specialtyList.value = specialityRepository.getCachedSpecialities()
        employeeList.value = employeeRepository.getCachedEmployees()
    }
}