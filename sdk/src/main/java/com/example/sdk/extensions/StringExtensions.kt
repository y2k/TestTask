package com.example.sdk.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.fixName(): String {
    return if (this.isNotEmpty() && this.length >= 2) {
        this.substring(0, 1).toUpperCase().plus(this.substring(1).toLowerCase())
    } else ""
}

fun String.fromStringToDate(): Date {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    return dateFormat.parse(this)
}

fun String.fixBirthday(): String {
    if (this.isEmpty()) {
        return ""
    }

    val regex = Regex(pattern = "([0-9]{2})-([0-9]{2})-([0-9]{4})")

    if (regex.containsMatchIn(this)) {
        return this
    } else {
        val dateFormatIncorrect = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dateFormatCorrect = SimpleDateFormat("dd-MM-yyyy", Locale.US)

        val correctDate = dateFormatIncorrect.parse(this)
        val correctStringDate = dateFormatCorrect.format(correctDate)

        return correctStringDate
    }
}