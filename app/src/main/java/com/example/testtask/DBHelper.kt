package com.example.testtask

import com.example.room.dao.EmployeeDao
import com.example.room.dao.SpecialityDao
import com.example.testtask.extensions.mapper.toDBModel
import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class DBHelper @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val specialityDao: SpecialityDao,
    private val specialityRepository: SpecialityRepository
) {

    fun writeResultToDB(result: ResponseResult) {
        for (i in 0 until result.items.size) {
            employeeDao.insertEmployee(result.items[i].toDBModel())
        }

        val specialityList: ArrayList<Specialty> = specialityRepository.getCachedSpecialities()
        for (i in 0 until specialityList.size) {
            specialityDao.insertSpeciality(specialityList[i].toDBModel())
        }
    }
}