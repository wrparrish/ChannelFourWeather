package com.ruthlessprogramming.domain

data class WeatherForDateRange(
        val city: City? = null,
        val cnt: Int? = null,
        val cod: String? = null,
        val message: Double? = null,
        val list: List<ListItem>? = null
)
