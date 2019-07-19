package com.example.testtask.domain.repository

import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality

interface SpecialityRepository {
    fun setSpecialities(specialties:ArrayList<Speciality>)

    fun setSpecialitiesFromEmployeeList(employees: List<Employee>)

    fun getSpecialities():ArrayList<Speciality>
}