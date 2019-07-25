package com.example.testtask.data.datasource.database.room.dao

import androidx.room.*
import com.example.testtask.data.datasource.database.room.model.SpecialtyRelationDB

@Dao
interface RelationDao {

    @Query("SELECT * FROM specialities_relation")
    abstract fun selectAllSpecialityRelation(): List<SpecialtyRelationDB>
}