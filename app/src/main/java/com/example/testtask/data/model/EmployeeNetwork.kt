package com.example.testtask.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeNetwork(
    @SerializedName("f_name")
    @Expose
    val firstName: String?,

    @SerializedName("l_name")
    @Expose
    val lastName: String?,

    @SerializedName("birthday")
    @Expose
    val birthday: String?,

    @SerializedName("avatr_url")
    @Expose
    val avatarUrl: String?,

    @SerializedName("specialty")
    @Expose
    val specialtyNetworkList: ArrayList<SpecialtyNetwork>?
)