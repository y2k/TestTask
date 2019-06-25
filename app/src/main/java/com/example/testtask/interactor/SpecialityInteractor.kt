package com.example.testtask.interactor

import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class SpecialityInteractor @Inject constructor(private val specialityRepository: SpecialityRepository) {

    fun cacheSpecialitiesToRepository(result: ResponseResult) {

        val specialities = result.items
            .flatMap { it.specialtyList.orEmpty() }
            .distinct()

        specialityRepository.cacheSpecialities(specialities as ArrayList<Specialty>)
    }
}