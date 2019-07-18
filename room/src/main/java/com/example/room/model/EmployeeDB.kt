package com.example.room.model

import androidx.room.*
import kotlin.collections.ArrayList

@Entity(tableName = com.example.room.EmployeeDatabase.TABLE_EMPLOYEES_NAME)
@TypeConverters(com.example.room.Converters::class)
data class EmployeeDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "f_name")
    var firstName: String?,

    @ColumnInfo(name = "l_name")
    var lastName: String?,

    @ColumnInfo(name = "birthday")
    var birthday: String?,

    @ColumnInfo(name = "avatr_url")
    var avatarUrl: String?,

    @ColumnInfo(name = "specialty")
    var specialtyDBList: ArrayList<SpecialtyDB>?
)