package com.example.testtask.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testtask.contracts.MainActivityContract
import com.example.testtask.interactor.EmployeeInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val employeeInteractor: EmployeeInteractor) : ViewModel() {

    private var view: MainActivityContract? = null

    fun setView(view: MainActivityContract) {
        this.view = view
    }

    fun getData() {
        view?.setLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            employeeInteractor.getEmployees()
            view?.setLoading(false)
            view?.onDataReady()
        }
    }

    fun onDestroy() {
        view = null
    }
}
