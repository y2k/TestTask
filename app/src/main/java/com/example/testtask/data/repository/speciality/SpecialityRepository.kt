package com.example.testtask.data.repository.speciality

import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Specialty

interface SpecialityRepository {
    fun setSpecialities(specialties: ArrayList<Specialty>)

    fun setSpecialitiesFromEmployeeList(employees: ArrayList<Employee>)

    fun getSpecialities():ArrayList<Specialty>
}