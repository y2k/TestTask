package com.example.testtask.data

import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import timber.log.Timber

fun Employee.toDBModel(id: Int): EmployeeDB {
    val specialtyList = this.specialtyList?.map { it.toDBModel() } as ArrayList<SpecialtyDB>
    return EmployeeDB(
        id = id,
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

fun EmployeeDB.toDomain(): Employee {
    Timber.e("LIST ALL: " + this.specialtyDBList?.size)
    val specialityList = this.specialtyDBList?.map { it.toDomain() }
    Timber.e("LIST: " + specialityList?.size)
    return Employee(
        firstName = this.firstName,
        lastName = this.lastName,
        birthday = this.birthday,
        avatarUrl = this.avatarUrl,
        specialtyList = specialityList as ArrayList<Speciality>
    )
}

fun SpecialtyDB.toDomain(): Speciality {
    return Speciality(
        specialityID = this.id,
        specialityName = this.specialityName
    )
}