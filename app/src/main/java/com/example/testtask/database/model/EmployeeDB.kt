package com.example.testtask.database.model

import androidx.room.*
import com.example.testtask.database.Converters
import com.example.testtask.database.EmployeeDatabase
import com.example.testtask.model.Specialty
import kotlin.collections.ArrayList

@Entity(tableName = EmployeeDatabase.TABLE_EMPLOYEES_NAME)
@TypeConverters(Converters::class)
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
) {
    constructor() : this(
        0, "", "", "", "", null
    )
}