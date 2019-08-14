package com.example.testtask.data.datasource.database

import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality

interface DBHelper {
    @Deprecated("")
    fun writeEmployeesToDB(employeeList: List<Employee>)

    @Deprecated("")
    fun writeSpecialitiesToDB(specialityList: List<Speciality>)

    @Deprecated("")
    fun readEmployeesFromDB(): List<Employee>
}