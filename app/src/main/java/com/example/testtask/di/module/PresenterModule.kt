package com.example.testtask.di.module

import com.example.testtask.presenter.MainPresenter
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainViewPresenter(employeeRepository: EmployeeRepository, specialityRepository: SpecialityRepository,dataBaseRepository: DataBaseRepository):
            MainPresenter = MainPresenter(employeeRepository,specialityRepository,dataBaseRepository)
}