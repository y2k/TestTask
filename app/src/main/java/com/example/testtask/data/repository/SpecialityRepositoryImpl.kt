package com.example.testtask.data.repository

import com.example.testtask.data.datasource.database.room.model.SpecialtyDB
import com.example.testtask.data.toDBModel
import com.example.testtask.data.datasource.database.room.DBHelper
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import com.example.testtask.domain.SpecialityRepository

import javax.inject.Inject

class SpecialityRepositoryImpl @Inject constructor(private val dbHelper: DBHelper) : SpecialityRepository {
    private var cachedSpecialities = arrayListOf<Speciality>()

    override fun setSpecialities(specialties: ArrayList<Speciality>) {
        cacheSpecialities(specialties)
    }

    override fun setSpecialitiesFromEmployeeList(employees: List<Employee>) {
        val specialties = employees
            .flatMap { it.specialtyList }
            .distinct() as ArrayList<Speciality>

        cacheSpecialities(specialties)
        saveSpecialitiesToDB(specialties)
    }

    //The only thing we can do is return cached value
    override fun getSpecialities() = cachedSpecialities

    private fun cacheSpecialities(specialties: ArrayList<Speciality>) {
        this.cachedSpecialities = specialties
    }

    private fun saveSpecialitiesToDB(specialties: ArrayList<Speciality>) {
        val convertedSpecialities = specialties.map { it.toDBModel() }
        dbHelper.writeSpecialitiesToDB(convertedSpecialities as ArrayList<SpecialtyDB>)
    }
}