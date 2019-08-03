package com.example.testtask.di.module

import com.example.sdk.core.network.NetworkHelper
import com.example.testtask.data.datasource.network.ApiService
import com.example.testtask.domain.interfaces.EmployeeRepository
import com.example.testtask.data.repository.EmployeeRepositoryImpl
import com.example.testtask.domain.interfaces.SpecialityRepository
import com.example.testtask.data.repository.SpecialityRepositoryImpl
import com.example.testtask.data.datasource.database.DBHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideEmployeeRepository(apiService: ApiService, dbHelper: DBHelper, networkHelper: NetworkHelper): EmployeeRepository =
        EmployeeRepositoryImpl(apiService, dbHelper,networkHelper)

    @Provides
    @Singleton
    fun provideSpecialityRepository(dbHelper: DBHelper): SpecialityRepository =
        SpecialityRepositoryImpl(dbHelper)
}