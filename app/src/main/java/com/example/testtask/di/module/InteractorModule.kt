package com.example.testtask.di.module

import com.example.testtask.domain.EmployeeRepository
import com.example.testtask.domain.interactor.EmployeeInteractorImpl
import com.example.testtask.domain.SpecialityRepository
import com.example.testtask.domain.interactor.SpecialityInteractorImpl
import com.example.testtask.view.EmployeeInteractor
import com.example.testtask.view.SpecialityInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideEmployeeInteractor(employeeRepository: EmployeeRepository, specialityRepository: SpecialityRepository): EmployeeInteractor =
        EmployeeInteractorImpl(employeeRepository, specialityRepository)

    @Provides
    fun provideSpecialityInteractor(specialityRepository: SpecialityRepository): SpecialityInteractor =
        SpecialityInteractorImpl(specialityRepository)
}