package com.example.testtask.view

import com.example.testtask.domain.model.Speciality

interface SpecialityInteractor {
    fun getSpecialities(): ArrayList<Speciality>
}