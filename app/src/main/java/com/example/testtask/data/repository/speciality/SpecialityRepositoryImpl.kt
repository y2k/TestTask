package com.example.testtask.data.repository.speciality

import com.example.room.DBHelper
import com.example.room.model.SpecialtyDB
import com.example.testtask.domain.toDBModel
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.data.model.SpecialtyNetwork

import javax.inject.Inject

class SpecialityRepositoryImpl @Inject constructor(private val dbHelper: DBHelper) : SpecialityRepository {
    private var cachedSpecialities = arrayListOf<SpecialtyNetwork>()

    override fun setSpecialities(specialtyNetworks: ArrayList<SpecialtyNetwork>) {
        cacheSpecialities(specialtyNetworks)
    }

    override fun setSpecialitiesFromEmployeeList(employeeNetworks: ArrayList<EmployeeNetwork>) {
        val specialties = employeeNetworks
            .flatMap { it.specialtyNetworkList.orEmpty() }
            .distinct() as ArrayList<SpecialtyNetwork>
        cacheSpecialities(specialties)
        saveSpecialitiesToDB(specialties)
    }

    //The only thing we can do is return cached value
    override fun getSpecialities() = cachedSpecialities

    private fun cacheSpecialities(specialtyNetworks: ArrayList<SpecialtyNetwork>) {
        this.cachedSpecialities = specialtyNetworks
    }

    private fun saveSpecialitiesToDB(specialtyNetworks: ArrayList<SpecialtyNetwork>) {
        val convertedSpecialities = specialtyNetworks.map { it.toDBModel() }
        dbHelper.writeSpecialitiesToDB(convertedSpecialities as ArrayList<SpecialtyDB>)
    }
}