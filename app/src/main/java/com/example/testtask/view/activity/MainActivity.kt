package com.example.testtask.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.view.dialog.ErrorConnectionDialog
import com.example.testtask.view.viewmodel.MainActivityViewModel
import com.example.testtask.view.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity(), OnInternetStateListener, CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkInternetConnection(this)
    }

    override fun onSuccessConnection() {
        App.get().injector?.inject(this)

        mainActivityViewModel = ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
        sharedViewModel = ViewModelProviders.of(this, factory)[SharedViewModel::class.java]

        mainActivityViewModel.progressBarLiveData.observe(this,
            Observer<Boolean> { state -> setLoading(state) })


        mainActivityViewModel.dataReadyLiveData.observe(this,
            Observer<Unit> {
                this.launch {
                    withContext(coroutineContext) {
                        sharedViewModel.init()
                    }
                }
            })

        mainActivityViewModel.errorLiveData.observe(this, Observer { error ->
            ErrorConnectionDialog.getInstance(error).show(supportFragmentManager, "ErrorTag")
        })
    }

    //Write realization later
    override fun onOfflineWorkClicked() {

    }

    private fun setLoading(state: Boolean) {
        progress.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
