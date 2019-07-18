package com.example.testtask.domain

import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork

fun EmployeeNetwork.toDBModel(): EmployeeDB {
    val specialtyList = this.specialtyNetworkList?.map { it.toDBModel() } as ArrayList<SpecialtyDB>
    return EmployeeDB(
        id = 0,
        firstName = this.firstName,
        lastName = this.lastName,
        birthday = this.birthday,
        avatarUrl = this.avatarUrl,
        specialtyDBList = specialtyList
    )
}


fun SpecialtyNetwork.toDBModel(): SpecialtyDB {
    return SpecialtyDB(
        id = 0,
        specialityID = this.specialityID,
        specialityName = this.specialityName
    )
}