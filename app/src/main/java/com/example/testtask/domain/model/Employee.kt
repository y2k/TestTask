package com.example.testtask.domain.model

data class Employee(
    val firstName: String?,
    val lastName: String?,
    val birthday: String?,
    val avatarUrl: String?,
    val specialtyList: ArrayList<Speciality>?
)