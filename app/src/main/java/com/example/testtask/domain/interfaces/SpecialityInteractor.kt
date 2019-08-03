package com.example.testtask.domain.interfaces

import com.example.testtask.domain.model.Speciality

interface SpecialityInteractor {
    fun getSpecialities(): List<Speciality>
}