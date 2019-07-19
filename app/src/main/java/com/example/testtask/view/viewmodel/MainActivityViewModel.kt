package com.example.testtask.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdk.other.Either
import com.example.sdk.other.SingleLiveEvent
import com.example.testtask.view.EmployeeInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val employeeInteractor: EmployeeInteractor) : ViewModel() {
    val progressBarLiveData = MutableLiveData<Boolean>()
    val dataReadyLiveData = SingleLiveEvent<Unit>()
    val errorLiveData = MutableLiveData<String>()

    init {
        progressBarLiveData.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val employeesListResult = employeeInteractor.getEmployees()
            progressBarLiveData.value = false
            when (employeesListResult) {
                is Either.Data -> {
                    dataReadyLiveData.call()
                }
                is Either.Error -> {
                    errorLiveData.value = employeesListResult.error.errorCode
                }
            }
        }
    }
}
