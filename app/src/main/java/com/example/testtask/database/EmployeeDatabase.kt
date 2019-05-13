package com.example.testtask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.model.Employee


@Database(
    entities = [Employee::class],
    version = 1
)
//@TypeConverters(Converters::class)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDaoAccess(): EmployeeDao

    companion object {
        const val DATABASE_NAME = "EmployeesDB"
        const val TABLE_EMPLOYEES_NAME = "employees"
    }
}
