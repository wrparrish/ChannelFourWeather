package com.ruthlessprogramming.data.retrofit.service

import com.ruthlessprogramming.domain.WeatherForDateRange
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    fun getWeather(@Query("zip") zipCode: String = "27526", @Query("units") units: String ="imperial", @Query("mode") mode: String = "json"): Observable<WeatherForDateRange>
}

