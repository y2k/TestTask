package com.example.testtask.repository

import com.example.testtask.model.ResponseResult
import com.example.testtask.network.GitlabApiService
import io.reactivex.Observable

class EmployeeRepository(private val apiService: GitlabApiService) {
    fun loadEmployees(): Observable<ResponseResult> {
        return apiService.loadData()
    }
}