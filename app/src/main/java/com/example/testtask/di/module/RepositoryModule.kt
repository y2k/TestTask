package com.example.testtask.di.module

import com.example.testtask.data.datasource.database.room.dao.EmployeeDao
import com.example.testtask.data.datasource.database.room.dao.SpecialityDao
import com.example.testtask.data.datasource.network.ApiService
import com.example.testtask.data.datasource.database.room.DBHelper
import com.example.testtask.data.datasource.database.room.dao.RelationDao
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
    fun provideEmployeeRepository(apiService: ApiService, dbHelper: DBHelper): EmployeeRepository =
        EmployeeRepositoryImpl(apiService, dbHelper)

    @Provides
    @Singleton
    fun provideSpecialityRepository(dbHelper: DBHelper): SpecialityRepository =
        SpecialityRepositoryImpl(dbHelper)

    @Provides
    @Singleton
    fun provideDBHelper(employeeDao: EmployeeDao, specialityDao: SpecialityDao,relationDao: RelationDao): DBHelper =
        DBHelper(employeeDao, specialityDao,relationDao)
}