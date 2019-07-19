package com.example.testtask.domain

import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality

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

fun Speciality.toDBModel(): SpecialtyDB {
    return SpecialtyDB(
        id = 0,
        specialityID = this.specialityID,
        specialityName = this.specialityName
    )
}


fun SpecialtyNetwork.toDomian(): Speciality {
    return Speciality(
        specialityID = this.specialityID,
        specialityName = this.specialityName
    )
}

fun EmployeeNetwork.toDomian(): Employee {
    val specialtyList = this.specialtyNetworkList?.map { it.toDomian() }
    return Employee(
        firstName = this.firstName,
        lastName = this.lastName,
        birthday = this.birthday,
        avatarUrl = this.avatarUrl,
        specialtyList = specialtyList as ArrayList<Speciality>?
    )
}