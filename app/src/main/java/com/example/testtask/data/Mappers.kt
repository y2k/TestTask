package com.example.testtask.data

import com.example.testtask.data.datasource.database.room.model.EmployeeDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyRelationDB
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import timber.log.Timber

fun Employee.toDBModel(id: Int): EmployeeDB {

    val specialtyList = this.specialtyList.let {
        if (it.isNullOrEmpty()) {
            emptyList()
        } else it.map { speciality -> speciality.toDBModel() }
    }

    return EmployeeDB(
        id = id,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        birthday = birthday ?: "",
        avatarUrl = avatarUrl ?: ""
    ).apply {
        specialtyDBList = specialtyList as ArrayList<SpecialtyDB>
    }
}

fun Speciality.toDBModel(): SpecialtyDB {
    return SpecialtyDB(
        id = 0,
        specialityID = this.specialityID ?: -1,
        specialityName = this.specialityName ?: ""
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
    val specialityList = this.specialtyDBList.map { it.toDomain() }
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

fun EmployeeDB.getRelationList(): List<SpecialtyRelationDB> {
    val specialityList = this.specialtyDBList
    return if (specialityList.isNullOrEmpty()) emptyList() else specialityList.map { it.toRelation(this.id) }
}

fun SpecialtyDB.toRelation(userID: Int): SpecialtyRelationDB {
    return SpecialtyRelationDB(0, specialityID = this.specialityID, employeeID = userID)
}