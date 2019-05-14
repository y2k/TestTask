package com.example.testtask.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.R
import com.example.testtask.database.EmployeeDatabase
import com.example.testtask.database.model.EmployeeDB
import com.example.testtask.database.model.SpecialtyDB
import com.example.testtask.model.Employee
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.network.GitlabApiService
import com.example.testtask.transport.SharedViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = GitlabApiService.create()
        val repository = EmployeeRepositoryProvider.provideEmployeeRepository(apiService)

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)

        //TODO: Add presenter when i will have time
        repository.loadEmployees()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        sharedViewModel.init(result)
                        fillDB(result)
                    } else {
                        //Show error
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun fillDB(result: ResponseResult) {
        //TODO: Add async, remove "allowMainThreadQueries"
        val db = EmployeeDatabase.getInstance(this)
        val employeeDao = db?.employeeDaoAccess()
        val specialityDao = db?.specialityDaoAccess()

        for (i in 0 until result.items.size) {
            employeeDao?.insertEmployee(employeeMapper(result.items[i]))
        }

        val specialityList = sharedViewModel.specialtyList.value

        for (i in 0 until specialityList!!.size) {
            specialityDao?.insertSpeciality(specialityMapper(specialityList[i]))
        }
    }

    private fun employeeMapper(employee: Employee): EmployeeDB {
        val specialtyList = employee.specialtyList.map { specialityMapper(it) } as ArrayList<SpecialtyDB>
        return EmployeeDB(0, employee.firstName, employee.lastName, employee.birthday, employee.avatarUrl, specialtyList)
    }

    private fun specialityMapper(specialty: Specialty): SpecialtyDB {
        return SpecialtyDB(0, specialty.specialityID, specialty.specialityName)
    }
}
