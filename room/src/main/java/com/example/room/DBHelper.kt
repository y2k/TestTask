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

    fun writeResultToDB(employeeList: ArrayList<EmployeeDB>, specialityList: ArrayList<SpecialtyDB>) {
        employeeList.forEach {
            employeeDao.insertEmployee(it)
        }

        specialityList.forEach {
            specialityDao.insertSpeciality(it)
        }
    }
}