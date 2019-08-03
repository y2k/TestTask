package com.example.testtask.di.module

import com.example.testtask.domain.interfaces.EmployeeRepository
import com.example.testtask.domain.interactor.EmployeeInteractorImpl
import com.example.testtask.domain.interfaces.SpecialityRepository
import com.example.testtask.domain.interactor.SpecialityInteractorImpl
import com.example.testtask.domain.interfaces.EmployeeInteractor
import com.example.testtask.domain.interfaces.SpecialityInteractor
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