package com.example.testtask.domain

import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality

interface SpecialityRepository {
    @Deprecated("")
    fun setSpecialities(specialties: List<Speciality>)

    @Deprecated("")
    fun setSpecialitiesFromEmployeeList(employees: List<Employee>)

    @Deprecated("")
    fun getSpecialities(): List<Speciality>

    @Deprecated("")
    fun cacheSpecialities(specialties: List<Speciality>)

    @Deprecated("")
    fun saveSpecialitiesToDB(specialties: List<Speciality>)
}