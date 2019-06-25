package com.example.testtask.interactor

import com.example.testtask.model.ResponseResult
import com.example.testtask.model.Specialty
import com.example.testtask.repository.DataBaseRepository
import com.example.testtask.repository.SpecialityRepository
import javax.inject.Inject

class DBInteractor @Inject constructor(private val dataBaseRepository: DataBaseRepository) {

    fun saveResultToDB(result: ResponseResult) {
        dataBaseRepository.writeResultToDB(result)
    }
}