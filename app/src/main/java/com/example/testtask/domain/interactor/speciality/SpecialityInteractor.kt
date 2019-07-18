package com.example.testtask.domain.interactor.speciality

import com.example.testtask.data.model.SpecialtyNetwork

interface SpecialityInteractor {
    fun getSpecialities(): ArrayList<SpecialtyNetwork>
}