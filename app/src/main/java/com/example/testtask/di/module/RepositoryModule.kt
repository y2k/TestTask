package com.example.testtask.di.module

import com.example.testtask.data.datasource.room.dao.EmployeeDao
import com.example.testtask.data.datasource.room.dao.SpecialityDao
import com.example.testtask.data.datasource.network.GitlabApiService
import com.example.testtask.data.datasource.room.DBHelper
import com.example.testtask.domain.EmployeeRepository
import com.example.testtask.data.repository.EmployeeRepositoryImpl
import com.example.testtask.domain.SpecialityRepository
import com.example.testtask.data.repository.SpecialityRepositoryImpl
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