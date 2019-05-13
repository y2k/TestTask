package com.example.testtask.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.R
import com.example.testtask.network.GitlabApiService
import com.example.testtask.transport.SharedViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.testtask.model.Response

class MainActivity : BaseActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = GitlabApiService.create()
        val repository = EmployeeRepositoryProvider.provideEmployeeRepository(apiService)

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)

        //TODO: Add presenter if i will have enough time
        repository.loadEmployees()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    initSharedViewModel(result)
                }, { error ->
                    error.printStackTrace()
                })
    }

    //TODO: Move it to presenter or VM if i will have enough time
    private fun initSharedViewModel(result: Response.Result) {
        sharedViewModel.employeeList.value = result

        //Create unique speciality list and set it into VM
        val uniqueSpecialityList = ArrayList<Response.Specialty>()

        for (employee in result.items) {
            val employeeSpecialityList = employee.specialtyList

            if (employeeSpecialityList.isNotEmpty()) {
                for (speciality in employeeSpecialityList) {
                    if (!uniqueSpecialityList.contains(speciality)) {
                        uniqueSpecialityList.add(speciality)
                    }
                }
            }
        }
        sharedViewModel.specialtyList.value = uniqueSpecialityList
    }
}
