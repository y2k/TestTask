package com.example.testtask.data.datasource.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testtask.data.datasource.database.room.dao.EmployeeDao
import com.example.testtask.data.datasource.database.room.dao.SpecialityDao
import com.example.testtask.data.datasource.database.room.model.EmployeeDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyRelationDB

@Database(entities = [EmployeeDB::class, SpecialtyDB::class, SpecialtyRelationDB::class], version = 7)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDaoAccess(): EmployeeDao
    abstract fun specialityDaoAccess(): SpecialityDao

    companion object {
        const val DATABASE_NAME = "EmployeesDB"
        const val TABLE_EMPLOYEES_NAME = "employees"
        const val TABLE_SPECIALITY_NAME = "specialities"
        const val TABLE_SPECIALITY_RELATION = "specialities_relation"
    }
}
