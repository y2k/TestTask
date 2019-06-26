package com.example.testtask.viewmodel.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.model.Employee
import com.example.testtask.model.Specialty
import com.example.room.DBHelper
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private var specialityRepository: SpecialityRepository,
    private var employeeRepository: EmployeeRepository,
    private var dbHelper: DBHelper
) : ViewModel() {

    val employeeList = MutableLiveData<ArrayList<Employee>>()
    val specialtyList = MutableLiveData<ArrayList<Specialty>>()
    val selectedEmployee = MutableLiveData<Employee>()

    fun init() {
        specialtyList.value = specialityRepository.getCachedSpecialities()
        employeeList.value = employeeRepository.getCachedEmployees()
    }
}