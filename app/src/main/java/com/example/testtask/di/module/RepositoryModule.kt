package com.example.testtask.di.module

import com.example.sdk.core.network.NetworkHelper
import com.example.testtask.data.datasource.network.ApiService
import com.example.testtask.domain.EmployeeRepository
import com.example.testtask.data.repository.EmployeeRepositoryImpl
import com.example.testtask.domain.SpecialityRepository
import com.example.testtask.data.repository.SpecialityRepositoryImpl
import com.example.testtask.data.datasource.database.room.DBHelper
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