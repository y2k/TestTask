package com.example.room.dao

import androidx.room.*
import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyRelationDB

@Dao
abstract class EmployeeDao {

    @Query("SELECT * FROM employees")
    abstract fun getAllEmployees(): List<EmployeeDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEmployee(employee: EmployeeDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEmployeeList(employeeList: List<EmployeeDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSpecialityRelation(specialtyRelationDB: SpecialtyRelationDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSpecialityRelationList(specialtyRelationDBList: List<SpecialtyRelationDB>)

    @Update
    abstract fun updateEmployee(employee: EmployeeDB)

    @Delete
    abstract fun deleteEmployee(employee: EmployeeDB)

    fun insertEmployeeWithSpeciality(employeeDB: EmployeeDB) {
        val specialities = employeeDB.specialtyDBList
    }
}