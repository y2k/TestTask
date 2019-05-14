package com.example.testtask.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.model.ResponseResult
import com.example.testtask.presenter.MainPresenter
import com.example.testtask.presenter.MainView
import com.example.testtask.transport.SharedViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        App.getInstance().injector.inject(this)
        mainPresenter.init(this)
        mainPresenter.getData()
    }

    override fun onDataReady(result: ResponseResult) {
        sharedViewModel.init(result)
        mainPresenter.fillDB(result, sharedViewModel.specialtyList.value ?: ArrayList())
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}
