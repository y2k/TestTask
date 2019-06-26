package com.example.testtask.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testtask.abstracts.Presenter
import com.example.testtask.contracts.MainActivityContract
import com.example.testtask.interactor.DBInteractor
import com.example.testtask.interactor.EmployeeInteractor
import com.example.testtask.interactor.SpecialityInteractor
import com.example.testtask.model.ResponseResult
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val employeeInteractor: EmployeeInteractor,
    private val specialityInteractor: SpecialityInteractor,
    private val databaseInteractor: DBInteractor
) : ViewModel(), Presenter {

    private var view: MainActivityContract? = null

    fun setView(view: MainActivityContract) {
        this.view = view
    }

    fun getData() {
        view?.setLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            onDataLoaded(employeeInteractor.getData())
            view?.setLoading(false)
        }
    }

    private fun onDataLoaded(result: ResponseResult) {
        employeeInteractor.cacheEmpoyeesToRepository(result)
        specialityInteractor.cacheSpecialitiesToRepository(result)
        databaseInteractor.saveResultToDB(result)
        view?.onDataReady()
    }

    override fun onDestroy() {
        view = null
    }
}
