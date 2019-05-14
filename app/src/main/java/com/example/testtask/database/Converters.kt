package com.example.testtask.database

import androidx.room.TypeConverter
import com.example.testtask.database.model.SpecialtyDB

class Converters {

    @TypeConverter
    fun fromSpecialityList(specialtyDBList: ArrayList<SpecialtyDB>): String {
        val namesList = specialtyDBList.map { it.specialityName }
        return namesList.joinToString()
    }

    @TypeConverter
    fun toSpecialityList(string: String): ArrayList<SpecialtyDB> {
        return ArrayList()
    }
}