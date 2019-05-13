package com.example.testtask.repository

import com.example.testtask.model.Response
import com.example.testtask.network.GitlabApiService
import io.reactivex.Observable

class EmployeeRepository(private val apiService: GitlabApiService) {
    fun loadEmployees(): Observable<Response.Result> {
        return apiService.loadData()
    }
}