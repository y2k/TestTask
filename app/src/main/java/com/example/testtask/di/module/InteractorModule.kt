package com.example.testtask.di.module

import com.example.testtask.interactor.EmployeeInteractor
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideEmployeeInteractor(employeeRepository: EmployeeRepository,specialityRepository: SpecialityRepository): EmployeeInteractor =
        EmployeeInteractor(employeeRepository,specialityRepository)
}