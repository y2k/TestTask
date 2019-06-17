package com.example.testtask.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.model.ResponseResult
import com.example.testtask.presenter.MainActivityPresenter
import com.example.testtask.contracts.MainActivityContract
import com.example.testtask.transport.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityContract {

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)
        sharedViewModel = ViewModelProviders.of(this, null)[SharedViewModel::class.java]

        mainActivityPresenter.setView(this)
        mainActivityPresenter.getData()
    }

    override fun onDataReady(result: ResponseResult) {
        mainActivityPresenter.saveEmployeesListToRepo(result)
        mainActivityPresenter.saveSpecialitiesListToRepo(result)
        mainActivityPresenter.saveResultToDB(result)
        sharedViewModel.inject()
        sharedViewModel.init()
    }

    override fun setLoading(state: Boolean) {
        progress.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        mainActivityPresenter.onDestroy()
        super.onDestroy()
    }
}
