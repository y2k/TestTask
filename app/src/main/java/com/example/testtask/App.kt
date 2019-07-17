package com.example.testtask

import android.app.Application
import com.example.testtask.di.component.ApplicationComponent
import com.example.testtask.di.component.DaggerApplicationComponent
import com.example.testtask.di.module.*
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class App : Application() {

    var injector: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        INSTANCE = this

        injector = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .roomModule(RoomModule(this))
            .build()

        JodaTimeAndroid.init(this)
    }

    companion object {
        private lateinit var INSTANCE: App
        fun get(): App = INSTANCE
    }
}
