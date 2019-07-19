package com.example.room.dao

import androidx.room.*
import com.example.room.model.SpecialtyDB

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM specialities")
    fun getAllSpecialities(): List<SpecialtyDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeciality(specialty: SpecialtyDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialityList(specialtyList: List<SpecialtyDB>)

    @Update
    fun updateSpeciality(specialty: SpecialtyDB)

    @Delete
    fun deleteSpeciality(specialty: SpecialtyDB)
}