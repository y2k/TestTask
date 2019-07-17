package com.example.testtask.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdk.components.SingleLiveEvent
import com.example.testtask.domain.interactor.employee.EmployeeInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val employeeInteractor: EmployeeInteractor) : ViewModel() {
    val progressBarLiveData = MutableLiveData<Boolean>()
    val dataReadyLiveData = SingleLiveEvent<Unit>()

    init {
        progressBarLiveData.value = true
        viewModelScope.launch(Dispatchers.Main) {
            employeeInteractor.getEmployees()
            progressBarLiveData.value = false
            dataReadyLiveData.call()
        }
    }
}
