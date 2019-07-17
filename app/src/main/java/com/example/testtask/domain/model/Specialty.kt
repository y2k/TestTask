package com.example.testtask.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Specialty(
    @SerializedName("specialty_id")
    @Expose
    val specialityID: Int,
    @SerializedName("name")
    @Expose
    val specialityName: String
)