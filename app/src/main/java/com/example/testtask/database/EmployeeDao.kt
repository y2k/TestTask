package com.example.testtask.database

import androidx.room.*
import com.example.testtask.model.Employee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee)

    @Update
    fun updateEmployee(employee: Employee)

    @Delete
    fun deleteEmployee(employee: Employee)
}