package com.example.testtask.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sdk.utils.isInternetAviable
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.view.dialog.ErrorConnectionDialog
import com.example.testtask.view.dialog.NoConnectionDialog
import com.example.testtask.view.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG_FRAGMENT_NO_CONNECTION: String = "NoConnectionTag"

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var noInternetConnectionDialog: NoConnectionDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)

        sharedViewModel = ViewModelProviders.of(this, factory)[SharedViewModel::class.java]

        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (isInternetAviable(this)) {
            if (::noInternetConnectionDialog.isInitialized) {
                noInternetConnectionDialog.dismiss()
            }
            onInternetChecked(false)
            return
        }

        noInternetConnectionDialog = NoConnectionDialog(callBack = {
            when (it) {
                NoConnectionDialog.NO_CONNECTION_EXIT -> closeApp()

                NoConnectionDialog.NO_CONNECTION_OFFLINE -> {
                    onInternetChecked(true)
                    noInternetConnectionDialog.dismiss()
                }

                NoConnectionDialog.NO_CONNECTION_RETRY -> {
                    if (!isInternetAviable(this)) {
                        showMessage(R.string.base_error_no_connection)
                    } else {
                        noInternetConnectionDialog.dismiss()
                    }
                }
            }
        })

        noInternetConnectionDialog.show(supportFragmentManager, TAG_FRAGMENT_NO_CONNECTION)
        noInternetConnectionDialog.isCancelable = false
    }

    private fun onInternetChecked(isOfflineMode: Boolean) {
        sharedViewModel.init(isOfflineMode)

        sharedViewModel.progressBarLiveData.observe(this,
            Observer<Boolean> { state -> setLoading(state) })

        sharedViewModel.errorLiveData.observe(this, Observer { error ->
            ErrorConnectionDialog.getInstance(error).show(supportFragmentManager, "ErrorTag")
        })
    }

    private fun setLoading(state: Boolean) {
        progress.visibility = if (state) View.VISIBLE else View.GONE
    }

    fun showMessage(id: Int) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show()
    }


    //It's single activity app, so close Activity equals close app
    private fun closeApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity()
        } else {
            this.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
