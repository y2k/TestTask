package com.example.testtask.interactor

import com.example.testtask.model.ResponseResult
import com.example.testtask.DBHelper
import javax.inject.Inject

class DBInteractor @Inject constructor(private val dbHelper: DBHelper) {

    fun saveResultToDB(result: ResponseResult) {
        dbHelper.writeResultToDB(result)
    }
}