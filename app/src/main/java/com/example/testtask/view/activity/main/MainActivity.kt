package com.example.testtask.view.activity.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sdk.utils.isInternetAviable
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.view.activity.BaseActivity
import com.example.testtask.viewmodel.MainActivityViewModel
import com.example.testtask.viewmodel.transport.SharedViewModel
import com.example.testtask.view.fragment.additional.NoConnectionDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), OnInternetStateListener {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.get().injector?.inject(this)
        mainActivityViewModel = ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
        sharedViewModel = ViewModelProviders.of(this, factory)[SharedViewModel::class.java]

        checkInternetConnection(this)
    }

    override fun onSuccess() {
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
