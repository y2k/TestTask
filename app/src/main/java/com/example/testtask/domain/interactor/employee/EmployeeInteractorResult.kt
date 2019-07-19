package com.example.testtask.domain.interactor.employee

import com.example.testtask.domain.model.Employee

sealed class EmployeeInteractorResult {
    data class Error(val error:String):EmployeeInteractorResult()

    data class Data(val employees:List<Employee>):EmployeeInteractorResult()
}