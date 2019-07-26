package com.example.testtask.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sdk.utils.isInternetAvailable
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

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var noInternetConnectionDialog: NoConnectionDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)

        sharedViewModel = ViewModelProviders.of(this, factory)[SharedViewModel::class.java]

        if (sharedViewModel.isInitiated) {
            onInternetChecked(sharedViewModel.isOfflineMode)
        } else {
            checkInternetConnection()
        }
    }

    private fun checkInternetConnection() {
        if (isInternetAvailable(this)) {
            closeNoInternetConnectionDialog()
            onInternetChecked(false)
            return
        }

        noInternetConnectionDialog = NoConnectionDialog()
        noInternetConnectionDialog.callBack = {
            when (it) {
                NoConnectionDialog.NO_CONNECTION_EXIT -> closeApp()

                NoConnectionDialog.NO_CONNECTION_OFFLINE -> {
                    noInternetConnectionDialog.dismiss()
                    onInternetChecked(true)
                }

                NoConnectionDialog.NO_CONNECTION_RETRY -> {
                    if (!isInternetAvailable(this@MainActivity)) {
                        showMessage(R.string.base_error_no_connection)
                    } else {
                        noInternetConnectionDialog.dismiss()
                        onInternetChecked(false)
                    }
                }
            }
        }

        noInternetConnectionDialog.show(supportFragmentManager, TAG_FRAGMENT_NO_CONNECTION)
    }

    private fun onInternetChecked(isOfflineMode: Boolean) {
        sharedViewModel.init(isOfflineMode)

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

    private fun closeNoInternetConnectionDialog() {
        if (::noInternetConnectionDialog.isInitialized) {
            noInternetConnectionDialog.dismiss()
        }
    }

    override fun onPause() {
        closeNoInternetConnectionDialog()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
