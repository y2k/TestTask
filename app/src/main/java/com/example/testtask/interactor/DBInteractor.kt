package com.example.testtask.interactor

import com.example.room.DBHelper
import com.example.room.model.EmployeeDB
import com.example.room.model.SpecialtyDB
import com.example.testtask.extensions.mapper.toDBModel
import com.example.testtask.repository.EmployeeRepository
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class DBInteractor @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val specialityRepository: SpecialityRepository,
    private val dbHelper: DBHelper
) {

    fun saveResultToDB() {
        val employeeList = employeeRepository.getCachedEmployees()
        val specialityList = specialityRepository.getCachedSpecialities()

        val employeeListConverted: ArrayList<EmployeeDB> = employeeList.map { it.toDBModel() } as ArrayList<EmployeeDB>
        val specialityListConverted: ArrayList<SpecialtyDB> = specialityList.map { it.toDBModel() } as ArrayList<SpecialtyDB>

        dbHelper.writeResultToDB(employeeListConverted, specialityListConverted)
    }
}