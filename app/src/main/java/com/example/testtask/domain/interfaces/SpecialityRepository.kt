package com.example.testtask.domain.interfaces

import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality

interface SpecialityRepository {
    fun setSpecialities(specialties:List<Speciality>)

    fun setSpecialitiesFromEmployeeList(employees: List<Employee>)

    fun getSpecialities():List<Speciality>
}