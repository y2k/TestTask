package com.example.testtask.di.component

import com.example.room.EmployeeDatabase
import com.example.testtask.App
import com.example.testtask.di.module.ApplicationModule
import com.example.testtask.di.module.PresenterModule
import com.example.testtask.di.module.RoomModule
import com.example.testtask.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PresenterModule::class, RoomModule::class])
interface ApplicationComponent {

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

    val db: EmployeeDatabase
}