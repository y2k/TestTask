package com.example.testtask.database

import androidx.room.*
import com.example.testtask.database.model.SpecialtyDB

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM specialities")
    fun getAllSpecialities(): List<SpecialtyDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeciality(specialty: SpecialtyDB)

    @Update
    fun updateSpeciality(specialty: SpecialtyDB)

    @Delete
    fun deleteSpeciality(specialty: SpecialtyDB)
}