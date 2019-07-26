package com.example.testtask.data.datasource.database.room.dao

import androidx.room.*
import com.example.testtask.data.datasource.database.room.model.SpecialtyRelationDB

@Dao
interface RelationDao {

    @Query("SELECT * FROM specialities_relation WHERE employee_id = :employeeID")
    fun selectAllRelationsForEmployeeByEmployeeId(employeeID:Int): List<SpecialtyRelationDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialityRelationList(specialtyRelationDBList: List<SpecialtyRelationDB>)
}