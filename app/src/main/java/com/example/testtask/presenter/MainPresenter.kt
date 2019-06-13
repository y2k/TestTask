package com.example.testtask.presenter

import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MainPresenter @Inject constructor(private val employeeRepository: EmployeeRepository,
                                        private val specialityRepository: SpecialityRepository,
                                        private val dataBaseRepository: DataBaseRepository) {

    private var mainActivity: MainView? = null

    fun setView(mainView: MainView) {
        mainActivity = mainView
    }

    fun getData() {
        //TODO: I think it's ok, but how can we work with errors? Maybe i should return to RX?
        mainActivity?.setLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            mainActivity?.onDataReady(employeeRepository.loadEmployees().await())
            mainActivity?.setLoading(false)
        }
    }

    fun saveEmployeesListToRepo(result: ResponseResult) {
        employeeRepository.cacheEmployees(result.items as ArrayList<Employee>)
    }

    fun saveSpecialitiesListToRepo(result: ResponseResult) {
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
        specialityRepository.cacheSpecialities(uniqueSpecialityList)
    }

    fun saveResultToDB(result: ResponseResult) {
        //TODO: Separate and rename it in the future
        dataBaseRepository.writeResultToDB(result)
    }

    fun onDestroy() {
        mainActivity = null
    }
}
