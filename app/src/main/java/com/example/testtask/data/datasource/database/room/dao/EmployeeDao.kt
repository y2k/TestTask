package com.example.testtask.data.datasource.database.room.dao

import androidx.room.*
import com.example.testtask.data.datasource.database.room.model.EmployeeDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyRelationDB

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<EmployeeDB>

    @Query("SELECT * FROM specialities_relation")
    fun selectAllSpecialityRelation(): List<SpecialtyRelationDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEmployee(employee: EmployeeDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployeeList(employeeList: List<EmployeeDB>)

    @Update
    fun updateEmployee(employee: EmployeeDB)

    @Delete
    fun deleteEmployee(employee: EmployeeDB)
}