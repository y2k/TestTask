package com.example.testtask.data.datasource.database.room

import com.example.testtask.data.datasource.database.room.model.EmployeeDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB

interface DBHelper {
    fun writeEmployeesToDB(employeeList: ArrayList<EmployeeDB>)

    fun writeSpecialitiesToDB(specialityList: ArrayList<SpecialtyDB>)

    fun readEmployeesFromDB(): List<EmployeeDB>
}