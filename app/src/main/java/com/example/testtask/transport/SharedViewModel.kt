package com.example.testtask.transport

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.model.Response

class SharedViewModel : ViewModel() {
    val employeeList = MutableLiveData<Response.Result>()
    val specialtyList = MutableLiveData<ArrayList<Response.Specialty>>()
    //TODO:We have't employee id to identify it, so now it's easily to save employee to VM. Find better way in the future.
    val selectedEmployee = MutableLiveData<Response.Employee>()
}