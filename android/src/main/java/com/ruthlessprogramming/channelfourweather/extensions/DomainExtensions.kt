package com.ruthlessprogramming.channelfourweather.extensions

import com.ruthlessprogramming.channelfourweather.R
import java.text.SimpleDateFormat
import java.util.*


fun Long.buildDayString(): String {
    var dayAbrev = ""
    var hours = ""

    val cal = GregorianCalendar.getInstance()
    cal.timeInMillis = this * 1000
    val dayInt = cal.get(Calendar.DAY_OF_WEEK)
    dayAbrev = when (dayInt) {
        1 -> "Sun"
        2 -> "Mon"
        3 -> "Tues"
        4 -> "Wed"
        5 -> "Thur"
        6 -> "Fri"
        7 -> "Sat"
        else -> ""
    }
    val sdf = SimpleDateFormat("h:mm a")
    hours = sdf.format(cal.time)
    return "$dayAbrev  $hours"
}


fun String.parseForecastIcon(): Int {
    return when {
        this.contains("rain") -> R.drawable.ic_cloud_rain
        this.contains("clear") -> R.drawable.ic_sun
        this.contains("clouds") -> R.drawable.ic_cloud
        else -> R.drawable.ic_sun
    }
}
