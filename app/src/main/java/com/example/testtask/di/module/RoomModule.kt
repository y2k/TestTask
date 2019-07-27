package com.example.testtask.di.module

import dagger.Provides
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import com.example.testtask.data.datasource.database.room.DBHelperImpl
import com.example.testtask.data.datasource.database.room.EmployeeDatabase
import com.example.testtask.data.datasource.database.room.dao.EmployeeDao
import com.example.testtask.data.datasource.database.room.dao.RelationDao
import com.example.testtask.data.datasource.database.room.dao.SpecialityDao
import com.example.testtask.data.datasource.database.room.DBHelper
import dagger.Module

@Module
class RoomModule(context: Application) {

    private var db: EmployeeDatabase = Room
            .databaseBuilder(context,
                    EmployeeDatabase::class.java,
                    EmployeeDatabase.DATABASE_NAME.plus(".db"))
            .fallbackToDestructiveMigration()
            .build()

    private val employeeDao = db.employeeDaoAccess()
    private val specialityDao = db.specialityDaoAccess()
    private val relationDao = db.relationDaoAccess()

    @Singleton
    @Provides
    fun providesRoomDatabase(): EmployeeDatabase = db

    @Provides
    fun providesEmpoyeeDao(): EmployeeDao = employeeDao

    @Provides
    fun provideSpecialityDao(): SpecialityDao = specialityDao

    @Provides
    fun provideRelationDao(): RelationDao = relationDao

    @Provides
    fun provideDBHelper(): DBHelper = DBHelperImpl(employeeDao,specialityDao,relationDao)
}