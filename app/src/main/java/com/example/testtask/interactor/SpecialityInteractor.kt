package com.example.testtask.interactor

import com.example.testtask.model.Specialty
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class SpecialityInteractor @Inject constructor(private val specialityRepository: SpecialityRepository) {
    fun getSpecialities(): ArrayList<Specialty> {
            return specialityRepository.getSpecialities()
    }
}