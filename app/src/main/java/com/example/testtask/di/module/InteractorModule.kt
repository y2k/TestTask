package com.example.testtask.di.module

import com.example.testtask.domain.interactor.EmployeeInteractor
import com.example.testtask.data.repository.EmployeeRepository
import com.example.testtask.data.repository.SpecialityRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideEmployeeInteractor(employeeRepository: EmployeeRepository,specialityRepository: SpecialityRepository): EmployeeInteractor =
        EmployeeInteractor(employeeRepository,specialityRepository)
}