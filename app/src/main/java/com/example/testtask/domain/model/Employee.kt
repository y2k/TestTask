package com.example.testtask.domain.model

data class Employee(
    var firstName: String?,
    var lastName: String?,
    var birthday: String?,
    var avatarUrl: String?,
    var specialtyList: List<Speciality>?
)