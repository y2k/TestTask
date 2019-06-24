package com.example.sdk.extensions

import org.joda.time.LocalDate
import org.joda.time.Years
import java.text.SimpleDateFormat
import java.util.*

//Date
fun Date.getAge(): Int {
    val c = Calendar.getInstance()
    c.time = this

    val day = c.get(Calendar.DAY_OF_MONTH)
    val monthDay = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)

    val birthDate = LocalDate(year, monthDay + 1, day)
    val now = LocalDate()
    val age = Years.yearsBetween(birthDate, now)
    return age.years
}

fun Date.fromDateToFormattedString(): String {
    val simpleDate = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    return simpleDate.format(this)
}