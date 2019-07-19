package com.example.testtask.data.network

import com.example.testtask.data.model.EmployeeNetwork
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("response")
    @Expose
    val items: List<EmployeeNetwork>
)