package com.example.testtask.data.repository.speciality

import com.example.room.DBHelper
import com.example.room.model.SpecialtyDB
import com.example.testtask.domain.toDBModel
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Specialty

import javax.inject.Inject

class SpecialityRepositoryImpl @Inject constructor(private val dbHelper: DBHelper) : SpecialityRepository {
    private var cachedSpecialities = arrayListOf<Specialty>()

    override fun setSpecialities(specialties: ArrayList<Specialty>) {
        cacheSpecialities(specialties)
    }

    override fun setSpecialitiesFromEmployeeList(employees: ArrayList<Employee>) {
        val specialties = employees
            .flatMap { it.specialtyList.orEmpty() }
            .distinct() as ArrayList<Specialty>
        cacheSpecialities(specialties)
        saveSpecialitiesToDB(specialties)
    }

    //The only thing we can do is return cached value
    override fun getSpecialities() = cachedSpecialities

    private fun cacheSpecialities(specialties: ArrayList<Specialty>) {
        this.cachedSpecialities = specialties
    }

    private fun saveSpecialitiesToDB(specialties: ArrayList<Specialty>) {
        val convertedSpecialities = specialties.map { it.toDBModel() }
        dbHelper.writeSpecialitiesToDB(convertedSpecialities as ArrayList<SpecialtyDB>)
    }
}