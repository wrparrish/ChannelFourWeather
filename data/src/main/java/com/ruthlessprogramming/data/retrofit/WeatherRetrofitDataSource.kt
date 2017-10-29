package com.ruthlessprogramming.data.retrofit

import com.ruthlessprogramming.businesslogic.data.WeatherNetworkDataSource
import com.ruthlessprogramming.data.retrofit.service.WeatherService
import com.ruthlessprogramming.domain.WeatherForDateRange
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by billyparrish on 10/28/17.
 */
class WeatherRetrofitDataSource @Inject constructor(private val weatherService: WeatherService): WeatherNetworkDataSource {
    override fun getAll(): Observable<List<WeatherForDateRange>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(key: String): Observable<WeatherForDateRange> {
        return weatherService.getWeather(zipCode = key)
    }

    override fun put(key: String, value: WeatherForDateRange): Observable<WeatherForDateRange> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(key: String): Observable<WeatherForDateRange> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}