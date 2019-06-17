package com.example.testtask.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.App
import com.example.testtask.model.Employee
import com.example.testtask.model.Specialty
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class SharedViewModel : ViewModel() {

    val employeeList = MutableLiveData<ArrayList<Employee>>()
    val specialtyList = MutableLiveData<ArrayList<Specialty>>()
    val selectedEmployee = MutableLiveData<Employee>()

    @Inject
    lateinit var specialityRepository: SpecialityRepository
    @Inject
    lateinit var employeeRepository: EmployeeRepository
    @Inject
    lateinit var dataBaseRepository: DataBaseRepository

    fun inject() {
        App.get().injector?.inject(this)
    }

    fun init() {
        specialtyList.value = specialityRepository.getCachedSpecialities()
        employeeList.value = employeeRepository.getCachedEmployees()
    }
}