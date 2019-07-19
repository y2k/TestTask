package com.example.testtask.di.module

import com.example.testtask.domain.EmployeeRepository
import com.example.testtask.domain.interactor.EmployeeInteractorImpl
import com.example.testtask.domain.SpecialityRepository
import com.example.testtask.view.EmployeeInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideEmployeeInteractor(employeeRepository: EmployeeRepository, specialityRepository: SpecialityRepository): EmployeeInteractor =
        EmployeeInteractorImpl(employeeRepository, specialityRepository)
}