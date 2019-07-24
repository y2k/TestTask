package com.example.testtask.data.datasource.room.model

import androidx.room.*
import com.example.testtask.data.datasource.room.Converters
import com.example.testtask.data.datasource.room.EmployeeDatabase

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
    var avatarUrl: String?
) {
    @Ignore
    var specialtyDBList: ArrayList<SpecialtyDB> = ArrayList()
}