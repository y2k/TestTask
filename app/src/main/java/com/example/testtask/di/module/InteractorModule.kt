package com.example.testtask.di.module

import com.example.testtask.domain.repository.EmployeeRepository
import com.example.testtask.domain.interactor.employee.EmployeeInteractorImpl
import com.example.testtask.domain.repository.SpecialityRepository
import com.example.testtask.domain.interactor.employee.EmployeeInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideEmployeeInteractor(employeeRepository: EmployeeRepository, specialityRepository: SpecialityRepository): EmployeeInteractor =
        EmployeeInteractorImpl(employeeRepository, specialityRepository)
}