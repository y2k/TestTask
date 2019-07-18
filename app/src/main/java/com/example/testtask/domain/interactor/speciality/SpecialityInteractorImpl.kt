package com.example.testtask.domain.interactor.speciality

import com.example.testtask.data.repository.speciality.SpecialityRepository
import com.example.testtask.data.model.SpecialtyNetwork
import javax.inject.Inject

class SpecialityInteractorImpl @Inject constructor(private val specialityRepository: SpecialityRepository) :
    SpecialityInteractor {
    override fun getSpecialities(): ArrayList<SpecialtyNetwork> {
        return specialityRepository.getSpecialities()
    }
}