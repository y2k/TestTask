package com.example.testtask.di.module

import dagger.Provides
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import com.example.testtask.data.datasource.database.room.EmployeeDatabase
import com.example.testtask.data.datasource.database.room.dao.EmployeeDao
import com.example.testtask.data.datasource.database.room.dao.SpecialityDao
import dagger.Module

@Module
class RoomModule(mApplication: Application) {

    private var db: EmployeeDatabase = Room
            .databaseBuilder(mApplication,
                    EmployeeDatabase::class.java,
                    EmployeeDatabase.DATABASE_NAME.plus(".db"))
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    private val employeeDao = db.employeeDaoAccess()
    private val specialityDao = db.specialityDaoAccess()

    @Singleton
    @Provides
    fun providesRoomDatabase(): EmployeeDatabase = db

    @Provides
    fun providesEmpoyeeDao(): EmployeeDao = employeeDao

    @Provides
    fun provideSpecialityDao(): SpecialityDao = specialityDao
}