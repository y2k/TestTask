package com.example.testtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testtask.database.model.EmployeeDB
import com.example.testtask.database.model.SpecialtyDB

@Database(entities = [EmployeeDB::class, SpecialtyDB::class], version = 4)
@TypeConverters(Converters::class)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDaoAccess(): EmployeeDao
    abstract fun specialityDaoAccess(): SpecialityDao

    //TODO:Implement throw Dagger later + move to own module
    companion object {
        const val DATABASE_NAME = "EmployeesDB"
        const val TABLE_EMPLOYEES_NAME = "employees"
        const val TABLE_SPECIALITY_NAME = "specialities"

        private var INSTANCE: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase? {
            if (INSTANCE == null) {
                synchronized(EmployeeDB::class) {
                    INSTANCE = Room
                            .databaseBuilder(
                                    context.applicationContext,
                                    EmployeeDatabase::class.java,
                                    DATABASE_NAME.plus(".db")
                            )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}
