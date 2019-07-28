package com.example.testtask.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sdk.core.network.NetworkHelper
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.view.dialog.ErrorDialog
import com.example.testtask.view.dialog.NoConnectionDialog
import com.example.testtask.view.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG_FRAGMENT_NO_CONNECTION: String = "NoConnectionTag"

    private lateinit var noInternetConnectionDialog: NoConnectionDialog
    private val noConnectionDialogCallback = { select: Int ->
        when (select) {
            NoConnectionDialog.NO_CONNECTION_EXIT -> closeApp()

            NoConnectionDialog.NO_CONNECTION_OFFLINE -> {
                noInternetConnectionDialog.dismiss()
                networkChecker.isOfflineModeEnabled = true
                onAppWorkingModeSelected()
            }

            NoConnectionDialog.NO_CONNECTION_RETRY -> {
                if (!networkChecker.isConnectedToNetwork()) {
                    showMessage(R.string.base_error_no_connection)
                } else {
                    noInternetConnectionDialog.dismiss()
                    networkChecker.isOfflineModeEnabled = false
                    onAppWorkingModeSelected()
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var networkChecker:NetworkHelper

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)

        //Check if NoConnectionDialog showing, and restore callback to it
        restoreNoConnectionDialog()

        sharedViewModel = ViewModelProviders.of(this, factory)[SharedViewModel::class.java]

        sharedViewModel.networkStatusLiveData.observe(this, Observer { isInternetAvailable ->
            if (isInternetAvailable) {
                networkChecker.isOfflineModeEnabled = false
                onAppWorkingModeSelected()
            } else {
                showNoConnectionDialog()
            }
        })
    }

    private fun showNoConnectionDialog() {
        noInternetConnectionDialog = NoConnectionDialog()
        noInternetConnectionDialog.callBack = noConnectionDialogCallback
        noInternetConnectionDialog.show(supportFragmentManager, TAG_FRAGMENT_NO_CONNECTION)
    }

    private fun onAppWorkingModeSelected() {
        sharedViewModel.setData()

        sharedViewModel.progressBarLiveData.observe(this,
            Observer<Boolean> { state -> setLoading(state) })

        sharedViewModel.errorLiveData.observe(this, Observer { failure ->
            ErrorDialog.getInstance(failure).show(supportFragmentManager, "ErrorTag")
        })
    }

    private fun setLoading(state: Boolean) {
        progress.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showMessage(id: Int) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show()
    }

    //It's single activity app, so close Activity equals close app
    fun closeApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity()
        } else {
            this.finish()
        }
    }

    private fun restoreNoConnectionDialog(){
        if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_NO_CONNECTION) != null) {
            noInternetConnectionDialog = (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_NO_CONNECTION) as NoConnectionDialog)
            noInternetConnectionDialog.callBack = noConnectionDialogCallback
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
