package com.example.testtask.di.module

import com.example.room.dao.EmployeeDao
import com.example.room.dao.SpecialityDao
import com.example.testtask.network.GitlabApiService
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideEmployeeRepository(gitlabApiService: GitlabApiService): EmployeeRepository = EmployeeRepository(gitlabApiService)

    @Provides
    @Singleton
    fun provideSpecialityRepository(): SpecialityRepository = SpecialityRepository()

    @Provides
    @Singleton
    fun provideDataBaseRepository(employeeDao: EmployeeDao, specialityDao: SpecialityDao, specialityRepository: SpecialityRepository):
            DataBaseRepository = DataBaseRepository(employeeDao, specialityDao, specialityRepository)
}