package com.example.testtask.extensions

import org.joda.time.LocalDate
import org.joda.time.Years
import java.util.*

//Date
fun Date.getAge(): Int {
    val c = Calendar.getInstance()
    c.time = this

    val day = c.get(Calendar.DAY_OF_MONTH)
    val daymonth = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)

    val birthdate = LocalDate(year, daymonth + 1, day)
    val now = LocalDate()
    val age = Years.yearsBetween(birthdate, now)
    return age.years
}