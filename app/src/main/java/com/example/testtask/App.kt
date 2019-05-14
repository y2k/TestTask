package com.example.testtask

import android.app.Application
import com.example.testtask.di.component.ApplicationComponent
import com.example.testtask.di.component.DaggerApplicationComponent
import com.example.testtask.di.module.ApplicationModule
import com.example.testtask.di.module.PresenterModule
import com.example.testtask.di.module.RoomModule
import net.danlew.android.joda.BuildConfig
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class App : Application() {

    val injector: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .roomModule(RoomModule(this))
            .presenterModule(PresenterModule())
            .build()
    }

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
