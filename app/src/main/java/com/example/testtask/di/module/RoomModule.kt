package com.example.testtask.di.module

import dagger.Provides
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import com.example.room.EmployeeDatabase
import com.example.room.dao.EmployeeDao
import com.example.room.dao.SpecialityDao
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