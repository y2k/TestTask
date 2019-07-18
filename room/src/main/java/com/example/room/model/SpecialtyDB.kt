package com.example.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.room.EmployeeDatabase

@Entity(tableName = EmployeeDatabase.TABLE_SPECIALITY_NAME)
data class SpecialtyDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "specialty_id")
    var specialityID: Int?,

    @ColumnInfo(name = "specialty_name")
    var specialityName: String?
)