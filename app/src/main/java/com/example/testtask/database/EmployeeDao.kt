package com.example.testtask.database

import androidx.room.*
import com.example.testtask.database.model.EmployeeDB

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<EmployeeDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeeDB)

    @Update
    fun updateEmployee(employee: EmployeeDB)

    @Delete
    fun deleteEmployee(employee: EmployeeDB)
}