package com.ruthlessprogramming.businesslogic.data

import com.ruthlessprogramming.domain.WeatherForDateRange
import io.reactivex.Observable

/**
 * Created by billyparrish on 10/28/17.
 */
interface WeatherNetworkDataSource : WeatherDataSource {

    override fun getAll(): Observable<List<WeatherForDateRange>>

    override fun get(key: String): Observable<WeatherForDateRange>

    override fun put(key: String, value: WeatherForDateRange): Observable<WeatherForDateRange>

    override fun remove(key: String): Observable<WeatherForDateRange>

}