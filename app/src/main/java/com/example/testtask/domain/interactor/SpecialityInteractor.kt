package com.example.testtask.domain.interactor

import com.example.testtask.domain.model.Specialty
import com.example.testtask.data.repository.SpecialityRepository
import javax.inject.Inject

class SpecialityInteractor @Inject constructor(private val specialityRepository: SpecialityRepository) {
    fun getSpecialities(): ArrayList<Specialty> {
            return specialityRepository.getSpecialities()
    }
}