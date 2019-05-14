package com.example.room.dao

import androidx.room.*
import com.example.room.model.EmployeeDB

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