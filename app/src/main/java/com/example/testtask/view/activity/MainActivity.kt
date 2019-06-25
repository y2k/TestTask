package com.example.testtask.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.sdk.utils.isInternetAviable
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.presenter.MainActivityPresenter
import com.example.testtask.contracts.MainActivityContract
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.transport.SharedViewModel
import com.example.testtask.view.fragment.additional.NoConnectionDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityContract {

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var noConnectionDialog: NoConnectionDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)
        sharedViewModel = ViewModelProviders.of(this, factory)[SharedViewModel::class.java]

        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (!isInternetAviable(this)) {
            noConnectionDialog = NoConnectionDialogFragment(callBack = {
                if (it == NoConnectionDialogFragment.NO_CONNECTION_EXIT) {
                    closeApp()
                } else {
                    if (!isInternetAviable(this)) {
                        showMessage("You still have no internet. Check your connection and try again!")
                    } else {
                        noConnectionDialog.dismiss()
                        onSuccessConnection()
                    }
                }
            })
            noConnectionDialog.show(supportFragmentManager, "NoConnectionTag")
            noConnectionDialog.isCancelable = false
        } else {
            onSuccessConnection()
        }
    }

    private fun onSuccessConnection() {
        mainActivityPresenter.setView(this)
        mainActivityPresenter.getData()
    }

    override fun onDataReady() {
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
