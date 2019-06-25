package com.example.testtask.di.module

import com.example.testtask.interactor.DBInteractor
import com.example.testtask.interactor.EmployeeInteractor
import com.example.testtask.interactor.SpecialityInteractor
import com.example.testtask.presenter.MainActivityPresenter
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
    fun provideMainViewPresenter(employeeInteractor: EmployeeInteractor,specialityInteractor: SpecialityInteractor,databaseInteractor:DBInteractor):
            MainActivityPresenter = MainActivityPresenter(employeeInteractor,specialityInteractor,databaseInteractor)
}