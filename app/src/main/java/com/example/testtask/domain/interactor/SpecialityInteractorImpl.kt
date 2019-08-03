package com.example.testtask.domain.interactor

import com.example.testtask.domain.interfaces.SpecialityRepository
import com.example.testtask.domain.model.Speciality
import com.example.testtask.domain.interfaces.SpecialityInteractor
import javax.inject.Inject

class SpecialityInteractorImpl @Inject constructor(private val specialityRepository: SpecialityRepository) :
    SpecialityInteractor {

    override fun getSpecialities(): List<Speciality> {
        return specialityRepository.getSpecialities()
    }
}