package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.room.dao.EmployeeDao
import com.example.room.dao.SpecialityDao
import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB

@Database(entities = [EmployeeDB::class, SpecialtyDB::class], version = 6)
@TypeConverters(Converters::class)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDaoAccess(): EmployeeDao
    abstract fun specialityDaoAccess(): SpecialityDao

    companion object {
        const val DATABASE_NAME = "EmployeesDB"
        const val TABLE_EMPLOYEES_NAME = "employees"
        const val TABLE_SPECIALITY_NAME = "specialities"
    }
}
