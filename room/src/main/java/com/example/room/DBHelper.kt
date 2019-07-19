package com.example.room

import com.example.room.dao.EmployeeDao
import com.example.room.dao.SpecialityDao
import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB
import javax.inject.Inject

class DBHelper @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val specialityDao: SpecialityDao
) {

    fun writeEmployeesToDB(employeeList: ArrayList<EmployeeDB>) {
        employeeDao.insertEmployeeList(employeeList)
    }

    fun writeSpecialitiesToDB(specialityList: ArrayList<SpecialtyDB>) {
        specialityDao.insertSpecialityList(specialityList)
    }
}