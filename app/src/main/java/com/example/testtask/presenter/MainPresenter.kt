package com.example.testtask.presenter

import com.example.room.EmployeeDatabase
import com.example.testtask.extensions.mapper.toDBModel
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.network.GitlabApiService
import com.example.testtask.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//TODO: Implenent Dagger to Database in future
class MainPresenter @Inject constructor(database: EmployeeDatabase) {

    private var db = database
    private var mainActivity: MainView? = null
    private val apiService = GitlabApiService.create()
    private val repository = BaseActivity.EmployeeRepositoryProvider.provideEmployeeRepository(apiService)

    fun init(mainView: MainView) {
        mainActivity = mainView
    }

    fun getData() {
        //TODO:Implement loader in the future
//        mainActivity.showLoading()
        repository.loadEmployees()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result != null) {
                    mainActivity?.onDataReady(result)
                } else {
                    //mainActivity.showError()
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    fun fillDB(result: ResponseResult, specialityList: ArrayList<Specialty>) {
        //TODO: Add async, remove "allowMainThreadQueries"
        val employeeDao = db.employeeDaoAccess()
        val specialityDao = db.specialityDaoAccess()

        for (i in 0 until result.items.size) {
            employeeDao.insertEmployee(result.items[i].toDBModel())
        }

        for (i in 0 until specialityList.size) {
            specialityDao.insertSpeciality(specialityList[i].toDBModel())
        }
    }

    fun onDestroy() {
        mainActivity = null
    }
}
