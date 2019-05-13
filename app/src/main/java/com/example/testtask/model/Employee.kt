package com.example.testtask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.testtask.database.EmployeeDatabase
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = EmployeeDatabase.TABLE_EMPLOYEES_NAME)
data class Employee(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hint_id")
    var hintId: Int = 0,

    @ColumnInfo(name = "f_name")
    @SerializedName("f_name")
    @Expose
    val firstName: String,

    @ColumnInfo(name = "l_name")
    @SerializedName("l_name")
    @Expose
    val lastName: String,

    @ColumnInfo(name = "birthday")
    @SerializedName("birthday")
    @Expose
    val birthday: String,

    @ColumnInfo(name = "avatr_url")
    @SerializedName("avatr_url")
    @Expose
    val avatarUrl: String,

    @Ignore
    @SerializedName("specialty")
    @Expose
    val specialtyList: ArrayList<Specialty>
)