package com.example.testtask.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpecialtyNetwork(
    @SerializedName("specialty_id")
    @Expose
    val specialityID: Int?,

    @SerializedName("name")
    @Expose
    val specialityName: String?
)