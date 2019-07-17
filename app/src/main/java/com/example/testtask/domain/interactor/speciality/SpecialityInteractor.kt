package com.example.testtask.domain.interactor.speciality

import com.example.testtask.domain.model.Specialty

interface SpecialityInteractor {
    fun getSpecialities(): ArrayList<Specialty>
}