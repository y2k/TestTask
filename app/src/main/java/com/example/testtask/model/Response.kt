package com.example.testtask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    data class Employee(
        @SerializedName("f_name")
        @Expose
        val firstName: String,
        @SerializedName("l_name")
        @Expose
        val lastName: String,
        @SerializedName("birthday")
        @Expose
        val birthday: String,
        @SerializedName("avatr_url")
        @Expose
        val avatarUrl: String,
        @SerializedName("specialty")
        @Expose
        val specialtyList: ArrayList<Specialty>
    )

    data class Specialty(
        @SerializedName("specialty_id")
        @Expose
        val specialityID: Int,
        @SerializedName("name")
        @Expose
        val specialityName: String
    )

    data class Result(
        @SerializedName("response")
        @Expose
        val items: List<Employee>
    )
}