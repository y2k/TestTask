package com.example.testtask.view.activity

import android.os.Bundle
import android.provider.Contacts
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sdk.utils.isInternetAviable
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.viewmodel.MainActivityViewModel
import com.example.testtask.viewmodel.transport.SharedViewModel
import com.example.testtask.view.fragment.additional.NoConnectionDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var noConnectionDialog: NoConnectionDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)
        mainActivityViewModel = ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
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
        mainActivityViewModel.getData()

        mainActivityViewModel.loaderLiveData.observe(this,
            Observer<Boolean> { state -> setLoading(state) })


        mainActivityViewModel.dataReadyLiveData.observe(this,
            Observer<Unit> {
                GlobalScope.launch(Dispatchers.Main) {
                    onDataReady()
                }
            })
    }

    private suspend fun onDataReady() {
        sharedViewModel.init()
    }

    private fun setLoading(state: Boolean) {
        progress.visibility = if (state) View.VISIBLE else View.GONE
    }
}
