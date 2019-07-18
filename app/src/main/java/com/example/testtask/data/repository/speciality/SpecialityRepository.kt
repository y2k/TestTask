package com.example.testtask.data.repository.speciality

import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork

interface SpecialityRepository {
    fun setSpecialities(specialtyNetworks: ArrayList<SpecialtyNetwork>)

    fun setSpecialitiesFromEmployeeList(employeeNetworks: ArrayList<EmployeeNetwork>)

    fun getSpecialities():ArrayList<SpecialtyNetwork>
}