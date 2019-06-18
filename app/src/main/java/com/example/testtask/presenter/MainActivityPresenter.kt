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
            view?.onDataReady(employeeRepository.loadEmployees().await())
            view?.setLoading(false)
        }
    }

    fun saveEmployeesListToRepo(result: ResponseResult) {
        employeeRepository.cacheEmployees(result.items as ArrayList<Employee>)
    }

    fun saveSpecialitiesListToRepo(result: ResponseResult) {
        val uniqueSpecialityList = ArrayList<Specialty>()

//        result.items
//            .flatMap { it.specialtyList ?: emptyList<Specialty>() }
//            .distinct()


        result.items.forEach { employee ->
            employee.specialtyList?.forEach { speciality ->
                if (!uniqueSpecialityList.contains(speciality)) {
                    uniqueSpecialityList.add(speciality)
                }
            }
        }

        specialityRepository.cacheSpecialities(uniqueSpecialityList)
    }

    fun saveResultToDB(result: ResponseResult) {
        dataBaseRepository.writeResultToDB(result)
    }

    override fun onDestroy() {
        view = null
    }
}
