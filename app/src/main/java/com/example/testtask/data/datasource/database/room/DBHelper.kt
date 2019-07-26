package com.example.testtask.data.datasource.database.room

import com.example.testtask.data.datasource.database.room.dao.EmployeeDao
import com.example.testtask.data.datasource.database.room.dao.RelationDao
import com.example.testtask.data.datasource.database.room.dao.SpecialityDao
import com.example.testtask.data.datasource.database.room.model.EmployeeDB
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB
import com.example.testtask.data.getRelationList
import javax.inject.Inject

class DBHelper @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val specialityDao: SpecialityDao,
    private val relationDao: RelationDao) {

    private val CODE_SELECT_IGNORE: Long = -1

    fun writeEmployeesToDB(employeeList: ArrayList<EmployeeDB>) {
        for(employee in employeeList){
            val resultCode = employeeDao.insertEmployee(employee)
            if (resultCode != CODE_SELECT_IGNORE) {
                relationDao.insertSpecialityRelationList(employee.getRelationList())
            }
        }
    }

    fun writeSpecialitiesToDB(specialityList: ArrayList<SpecialtyDB>) {
        specialityDao.insertSpecialityList(specialityList)
    }

    fun readEmployeesFromDB(): List<EmployeeDB> {
        val resultEmployeeList = ArrayList<EmployeeDB>()

        val employeesFromBD = employeeDao.getAllEmployees()
        employeesFromBD.forEach { employee ->
            employee.specialtyDBList = ArrayList()
            val employeeRelations = relationDao.selectAllRelationsForEmployeeByEmployeeId(employee.id)
            employeeRelations.forEach {
                employee.specialtyDBList.add(specialityDao.getSpecialityById(it.specialityID))
            }
            resultEmployeeList.add(employee)
        }
        return resultEmployeeList
    }
}