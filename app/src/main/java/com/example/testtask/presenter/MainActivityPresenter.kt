package com.example.testtask.presenter

import com.example.testtask.abstracts.Presenter
import com.example.testtask.contracts.MainActivityContract
import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val specialityRepository: SpecialityRepository,
    private val dataBaseRepository: DataBaseRepository
) :
    Presenter {

    private var view: MainActivityContract? = null

    fun setView(view: MainActivityContract) {
        this.view = view
    }

    fun getData() {
        view?.setLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            onDataLoaded(employeeRepository.loadEmployees().await())
            view?.setLoading(false)
        }
    }

    private fun onDataLoaded(result: ResponseResult) {
        saveEmployeesListToRepo(result)
        saveSpecialitiesListToRepo(result)
        saveResultToDB(result)
        view?.onDataReady()
    }

    private fun saveEmployeesListToRepo(result: ResponseResult) {
        employeeRepository.cacheEmployees(result.items as ArrayList<Employee>)
    }

    private fun saveSpecialitiesListToRepo(result: ResponseResult) {

        val specialities = result.items
            .flatMap { it.specialtyList.orEmpty() }
            .distinct()

        specialityRepository.cacheSpecialities(specialities as ArrayList<Specialty>)
    }

    private fun saveResultToDB(result: ResponseResult) {
        dataBaseRepository.writeResultToDB(result)
    }

    override fun onDestroy() {
        view = null
    }
}
