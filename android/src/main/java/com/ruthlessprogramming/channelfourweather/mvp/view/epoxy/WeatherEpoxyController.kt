package com.ruthlessprogramming.channelfourweather.mvp.view.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.ruthlessprogramming.channelfourweather.extensions.buildDayString
import com.ruthlessprogramming.channelfourweather.extensions.parseForecastIcon
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel
import com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models.CarouselModel_
import com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models.CurrentWeather_
import com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models.DailyWeatherModel_
import com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models.LoadingView_

class WeatherEpoxyController : TypedEpoxyController<WeatherModel.State>() {

    override fun buildModels(state: WeatherModel.State) {
        if (state.isLoading) {
            add(LoadingView_()
                    .id("loadingViewModel")
                    .progressString("Getting the current weather!"))
            return
        }

        val currentWeatherDescription = state.currentWeather?.weather?.first()?.description ?: ""

        add(CurrentWeather_()
                .id("currentWeatherModel")
                .summaryString(currentWeatherDescription)
                .cityName(state.currentCity.name ?: "")
                .iconDrawable(currentWeatherDescription.parseForecastIcon())
                .temp(state.currentWeather?.main?.temp ?: 0.0)
                .wind(state.currentWeather?.wind?.speed ?: 0.0)
        )

        val carouselList = buildListModels(state)
        add(CarouselModel_()
                .id("dayModels")
                .models(carouselList))
    }

    private fun buildListModels(state: WeatherModel.State): List<DailyWeatherModel_> {
        val itemList = state.data.list
        val itemsToReturn: ArrayList<DailyWeatherModel_> = ArrayList()
        if (itemList != null && itemList.isNotEmpty()) {
            itemList.mapTo(itemsToReturn) {
                DailyWeatherModel_()
                        .id(it.dt)
                        .summaryString(it.weather?.first()?.description ?: "")
                        .dayString(it.dt?.buildDayString() ?: "")
                        .iconDrawable(it.weather?.first()?.description?.parseForecastIcon() ?: 0)
                        .tempString("${it.main?.temp?.toInt()}")
            }
        }

        return itemsToReturn
    }


}