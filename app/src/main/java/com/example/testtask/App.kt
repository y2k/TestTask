package com.example.testtask

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        JodaTimeAndroid.init(this)
    }

    companion object {

        private var instance: App? = null

        fun getInstance(): App {
            if (instance == null) {
                instance = App()
            }
            return instance as App
        }
    }
}