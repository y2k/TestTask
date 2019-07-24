package com.example.testtask.data.datasource.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtask.data.datasource.room.EmployeeDatabase

@Entity(tableName = EmployeeDatabase.TABLE_SPECIALITY_RELATION)
data class SpecialtyRelationDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "employee_id")
    var employeeID: Int,

    @ColumnInfo(name = "specialty_id")
    var specialityID: Int
)