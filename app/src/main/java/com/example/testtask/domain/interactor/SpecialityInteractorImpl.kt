package com.example.testtask.domain.interactor

import com.example.testtask.domain.SpecialityRepository
import com.example.testtask.domain.model.Speciality
import com.example.testtask.view.SpecialityInteractor
import javax.inject.Inject

class SpecialityInteractorImpl @Inject constructor(private val specialityRepository: SpecialityRepository) : SpecialityInteractor {

    override fun getSpecialities(): ArrayList<Speciality> {
        return specialityRepository.getSpecialities()
    }
}