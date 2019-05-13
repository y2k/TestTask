package com.example.testtask.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.R
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.network.GitlabApiService
import com.example.testtask.transport.SharedViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

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
                sharedViewModel.init(result)
            }, { error ->
                error.printStackTrace()
            })
    }
}
