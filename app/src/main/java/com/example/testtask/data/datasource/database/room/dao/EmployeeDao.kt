package com.example.testtask.data.datasource.database.room.dao

import androidx.room.*
import com.example.testtask.data.datasource.database.room.model.EmployeeDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyRelationDB
import com.example.testtask.data.getRelationList

@Dao
abstract class EmployeeDao {
    private val CODE_SELECT_IGNORE: Long = -1

    @Query("SELECT * FROM employees")
    abstract fun getAllEmployees(): List<EmployeeDB>

    @Query("SELECT * FROM specialities_relation")
    abstract fun selectAllSpecialityRelation(): List<SpecialtyRelationDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertEmployee(employee: EmployeeDB): Long

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


    @Transaction
    open fun insertEmployeeListWithSpecialities(employeeList: List<EmployeeDB>) {
        for (employee in employeeList) {
            val resCode = insertEmployee(employee)
            if (resCode != CODE_SELECT_IGNORE) {
                insertSpecialityRelationList(employee.getRelationList())
            }
        }
    }
}