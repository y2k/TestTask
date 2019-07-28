package com.example.testtask.di.component

import com.example.testtask.di.module.*
import com.example.testtask.view.activity.MainActivity
import com.example.testtask.view.fragment.EmployeeFragment
import com.example.testtask.view.fragment.EmployeeListFragment
import com.example.testtask.view.fragment.SpecialityListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [   ContextModule::class,
        RoomModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        InteractorModule::class]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(specialityListFragment: SpecialityListFragment)
    fun inject(employeeListFragment: EmployeeListFragment)
    fun inject(employeeFragment: EmployeeFragment)
}