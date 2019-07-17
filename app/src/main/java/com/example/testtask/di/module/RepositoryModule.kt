package com.example.testtask.di.module

import com.example.room.dao.EmployeeDao
import com.example.room.dao.SpecialityDao
import com.example.testtask.data.network.GitlabApiService
import com.example.room.DBHelper
import com.example.testtask.data.repository.employee.EmployeeRepository
import com.example.testtask.data.repository.employee.EmployeeRepositoryImpl
import com.example.testtask.data.repository.speciality.SpecialityRepository
import com.example.testtask.data.repository.speciality.SpecialityRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideEmployeeRepository(gitlabApiService: GitlabApiService, dbHelper: DBHelper): EmployeeRepository =
        EmployeeRepositoryImpl(gitlabApiService, dbHelper)

    @Provides
    @Singleton
    fun provideSpecialityRepository(dbHelper: DBHelper): SpecialityRepository =
        SpecialityRepositoryImpl(dbHelper)

    @Provides
    @Singleton
    fun provideDBHelper(employeeDao: EmployeeDao, specialityDao: SpecialityDao): DBHelper =
        DBHelper(employeeDao, specialityDao)
}