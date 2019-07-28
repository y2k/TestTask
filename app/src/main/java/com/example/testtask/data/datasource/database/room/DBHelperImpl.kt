package com.example.testtask.data.datasource.database.room

import com.example.testtask.data.datasource.database.DBHelper
import com.example.testtask.data.datasource.database.room.dao.EmployeeDao
import com.example.testtask.data.datasource.database.room.dao.RelationDao
import com.example.testtask.data.datasource.database.room.dao.SpecialityDao
import com.example.testtask.data.datasource.database.room.model.SpecialtyDB
import com.example.testtask.data.getRelationList
import com.example.testtask.data.toDBModel
import com.example.testtask.data.toDomain
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import javax.inject.Inject

class DBHelperImpl @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val specialityDao: SpecialityDao,
    private val relationDao: RelationDao) : DBHelper {

    private val CODE_SELECT_IGNORE: Long = -1

    override fun writeEmployeesToDB(employeeList: List<Employee>) {
        //We can't use map here because we need index (broken API, employee without ID)
        for (i in employeeList.indices) {
            val convertedEmployee = employeeList[i].toDBModel(i)
            val resultCode = employeeDao.insertEmployee(convertedEmployee)
            if (resultCode != CODE_SELECT_IGNORE) {
                relationDao.insertSpecialityRelationList(convertedEmployee.getRelationList())
            }
        }
    }

    override fun writeSpecialitiesToDB(specialityList: List<Speciality>) {
        val convertedSpecialities = specialityList.map { it.toDBModel() }
        specialityDao.insertSpecialityList(convertedSpecialities)
    }

    override fun readEmployeesFromDB(): List<Employee> {
        val resultEmployeeList = ArrayList<Employee>()
        val employeesFromBD = employeeDao.getAllEmployees()

        employeesFromBD.forEach { employee ->
            val employeeRelations = relationDao.selectAllRelationsForEmployeeByEmployeeId(employee.id)
            employeeRelations.forEach {
                (employee.specialtyDBList as ArrayList<SpecialtyDB>).add(specialityDao.getSpecialityById(it.specialityID))
            }
            resultEmployeeList.add(employee.toDomain())
        }

        return resultEmployeeList
    }
}