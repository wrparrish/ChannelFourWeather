package com.ruthlessprogramming.data.retrofit.service

import com.ruthlessprogramming.domain.WeatherForDateRange
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by billyparrish on 10/28/17.
 */
interface WeatherService {

    @GET("forecast")
    fun getWeather(@Query("zip") zipCode: String = "27526", @Query("units") units: String ="imperial", @Query("mode") mode: String = "json"): Observable<WeatherForDateRange>
}

