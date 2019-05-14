package com.example.testtask.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty

class SharedViewModel : ViewModel() {
    val employeeList = MutableLiveData<ResponseResult>()
    val specialtyList = MutableLiveData<ArrayList<Specialty>>()
    //TODO:We have't employee id to identify it, so now it's easily to save selected employee to VM. Find better way in the future.
    val selectedEmployee = MutableLiveData<Employee>()

    fun init(result: ResponseResult) {
        specialtyList.value = ArrayList()
        employeeList.value = result

        //Create unique speciality list and set it into VM
        val uniqueSpecialityList = ArrayList<Specialty>()

        for (employee in result.items) {
            val employeeSpecialityList = employee.specialtyList

            if (!employeeSpecialityList.isNullOrEmpty()) {
                for (speciality in employeeSpecialityList) {
                    if (!uniqueSpecialityList.contains(speciality)) {
                        uniqueSpecialityList.add(speciality)
                    }
                }
            }
        }
        if (uniqueSpecialityList.isNotEmpty()) {
            specialtyList.value = uniqueSpecialityList
        }
    }
}