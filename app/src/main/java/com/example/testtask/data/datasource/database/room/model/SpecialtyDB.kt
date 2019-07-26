package com.example.testtask.data.datasource.database.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.testtask.data.datasource.database.room.EmployeeDatabase

@Entity(
    tableName = EmployeeDatabase.TABLE_SPECIALITY_NAME,
    indices = [Index(value = ["specialty_id"], unique = true)]
)
data class SpecialtyDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "specialty_id")
    var specialityID: Int,

    @ColumnInfo(name = "specialty_name")
    var specialityName: String
)