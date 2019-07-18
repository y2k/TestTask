package com.example.sdk.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.fixName(): String {
    return if (this.isNotEmpty() && this.length >= 2) {
        this.substring(0, 1).toUpperCase().plus(this.substring(1).toLowerCase())
    } else ""
}

//Strings
fun String.fromStringToDate(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return dateFormat.parse(this)
}