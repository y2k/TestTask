package com.example.testtask.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtask.database.EmployeeDatabase

@Entity(tableName = EmployeeDatabase.TABLE_SPECIALITY_NAME)
data class SpecialtyDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var specialityID: Int = 0,

    @ColumnInfo(name = "specialty_id")
    var specialityId: Int = 0,

    @ColumnInfo(name = "specialty_name")
    var specialityName: String
) {
    constructor() : this(
        0, 0, ""
    )
}