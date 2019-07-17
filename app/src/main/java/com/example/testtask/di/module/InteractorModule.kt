package com.example.testtask.di.module

import com.example.testtask.data.repository.employee.EmployeeRepository
import com.example.testtask.domain.interactor.employee.EmployeeInteractorImpl
import com.example.testtask.data.repository.speciality.SpecialityRepository
import com.example.testtask.domain.interactor.employee.EmployeeInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideEmployeeInteractor(employeeRepository: EmployeeRepository, specialityRepository: SpecialityRepository): EmployeeInteractor =
        EmployeeInteractorImpl(employeeRepository, specialityRepository)
}