package com.example.testtask.domain.interactor.speciality

import com.example.testtask.domain.repository.SpecialityRepository
import com.example.testtask.domain.model.Speciality
import javax.inject.Inject

class SpecialityInteractorImpl @Inject constructor(private val specialityRepository: SpecialityRepository) :
    SpecialityInteractor {
    override fun getSpecialities(): ArrayList<Speciality> {
        return specialityRepository.getSpecialities()
    }
}