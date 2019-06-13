package com.example.testtask.di.component

import com.example.testtask.di.module.*
import com.example.testtask.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PresenterModule::class, RoomModule::class, ServiceModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}