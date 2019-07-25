package com.example.testtask.data.datasource.database.room.dao

import androidx.room.*
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM specialities")
    fun getAllSpecialities(): List<SpecialtyDB>

    @Query("SELECT * FROM specialities WHERE specialty_id = :specialityID")
    fun getSpecialityById(specialityID:Int): SpecialtyDB

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSpeciality(specialty: SpecialtyDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSpecialityList(specialtyList: List<SpecialtyDB>)

    @Update
    fun updateSpeciality(specialty: SpecialtyDB)

    @Delete
    fun deleteSpeciality(specialty: SpecialtyDB)
}