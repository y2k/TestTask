package com.example.testtask.repository

import com.example.testtask.model.Specialty

import javax.inject.Inject

class SpecialityRepository @Inject constructor() {
    private lateinit var specialityList: ArrayList<Specialty>

    fun cacheSpecialities(specialties: ArrayList<Specialty>) {
        this.specialityList = specialties
    }

    fun getCachedSpecialities() = specialityList
}