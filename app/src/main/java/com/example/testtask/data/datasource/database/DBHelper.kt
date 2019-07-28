package com.example.testtask.data.datasource.database

import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality

interface DBHelper {
    fun writeEmployeesToDB(employeeList: List<Employee>)

    fun writeSpecialitiesToDB(specialityList: List<Speciality>)

    fun readEmployeesFromDB(): List<Employee>
}