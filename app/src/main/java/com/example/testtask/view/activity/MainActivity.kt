package com.example.testtask.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.model.ResponseResult
import com.example.testtask.presenter.MainPresenter
import com.example.testtask.presenter.MainView
import com.example.testtask.transport.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)

        mainPresenter.setView(this)
        mainPresenter.getData()
    }

    override fun onDataReady(result: ResponseResult) {
        sharedViewModel.init(result)
        mainPresenter.saveEmployeesListToRepo(result)
        mainPresenter.saveSpecialitiesListToRepo(result)
        mainPresenter.saveResultToDB(result)
    }

    override fun setLoading(state: Boolean) {
        progress.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}
