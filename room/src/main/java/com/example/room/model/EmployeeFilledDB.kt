package com.example.room.model

import androidx.room.Embedded
import androidx.room.Relation

class EmployeeFilledDB{
    @Embedded
    var employeeDB: EmployeeDB? = null

    @Relation(parentColumn = "", entityColumn = "Bar.id")
    var bar: List<SpecialtyDB>? = null
}