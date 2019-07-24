package com.example.testtask.data.datasource.room

import com.example.testtask.data.datasource.room.dao.EmployeeDao
import com.example.testtask.data.datasource.room.dao.SpecialityDao
import com.example.testtask.data.datasource.room.model.EmployeeDB
import com.example.testtask.data.datasource.room.model.SpecialtyDB
import javax.inject.Inject

class DBHelper @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val specialityDao: SpecialityDao
) {

    fun writeEmployeesToDB(employeeList: ArrayList<EmployeeDB>) {
//        employeeDao.insertEmployeeList(employeeList)
        for (employee in employeeList) {
            employeeDao.insertEmployeeWithSpeciality(employee)
        }
    }

    fun writeSpecialitiesToDB(specialityList: ArrayList<SpecialtyDB>) {
        specialityDao.insertSpecialityList(specialityList)
    }

    fun readEmployeesFromDB(): List<EmployeeDB> {
        return employeeDao.selectEmployeesWithSpeciality()
    }
}