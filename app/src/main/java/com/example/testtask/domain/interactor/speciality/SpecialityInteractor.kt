package com.example.testtask.domain.interactor.speciality

import com.example.testtask.domain.model.Speciality

interface SpecialityInteractor {
    fun getSpecialities(): ArrayList<Speciality>
}