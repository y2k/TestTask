package com.example.room.model

import androidx.room.*
import com.example.room.Converters
import com.example.room.EmployeeDatabase
import kotlin.collections.ArrayList

@Entity(tableName = EmployeeDatabase.TABLE_EMPLOYEES_NAME)
@TypeConverters(Converters::class)
data class EmployeeDB(

    @PrimaryKey
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