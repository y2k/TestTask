package com.example.testtask.extensions.mapper

import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB
import com.example.testtask.model.Employee
import com.example.testtask.model.Specialty

fun Employee.toDBModel(): EmployeeDB {
    val specialtyList = this.specialtyList?.map { it.toDBModel() } as ArrayList<SpecialtyDB>
    return EmployeeDB(
        id = 0,
        firstName = this.firstName,
        lastName = this.lastName,
        birthday = this.birthday,
        avatarUrl = this.avatarUrl,
        specialtyDBList = specialtyList
    )
}


fun Specialty.toDBModel(): SpecialtyDB {
    return SpecialtyDB(
        id = 0,
        specialityID = this.specialityID,
        specialityName = this.specialityName
    )
}