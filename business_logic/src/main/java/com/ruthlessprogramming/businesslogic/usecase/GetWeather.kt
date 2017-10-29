package com.ruthlessprogramming.businesslogic.usecase

import com.ruthlessprogramming.businesslogic.data.WeatherNetworkDataSource
import com.ruthlessprogramming.domain.WeatherForDateRange
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GetWeather @Inject constructor(private val network: WeatherNetworkDataSource) : UseCase<GetWeather.Request, GetWeather.Response> {

    override fun execute(request: Request): Observable<Response> {
        return network.get(request.zipCode)
                .map { Response(it) }
                .subscribeOn(Schedulers.io())
    }

    class Request(val zipCode: String) : UseCase.Request
    class Response(val data: WeatherForDateRange) : UseCase.Response

}