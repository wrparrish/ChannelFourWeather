package com.ruthlessprogramming.channelfourweather

import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel
import com.ruthlessprogramming.domain.City
import com.ruthlessprogramming.domain.WeatherForDateRange
import org.junit.Test

import org.junit.Assert.*


class WeatherStateTests {


    @Test
    fun itShouldInitializeWithDefaults() {
        val model = WeatherModel()
        val givenState = model.state

        assert(givenState.data.city == null)
    }

    @Test
    fun itShouldSetForecastData() {
        val model = WeatherModel()

        val givenState = model.state
        val givenCityName = givenState.currentCity.name
        val givenWeather = WeatherForDateRange(city = City(name = "Durham"))

        model.dispatch(WeatherModel.Action.WeatherSuccess(givenWeather))

        val stateFulCityName = model.state.currentCity.name
        assertTrue(givenCityName != stateFulCityName)
    }

    @Test
    fun itShouldNotLoadByDefault() {
        val model = WeatherModel()

        val givenState = model.state
        val givenLoading = givenState.isLoading
        assertFalse(givenLoading)
    }

    @Test
    fun itShouldLoad() {
        val model = WeatherModel()

        model.dispatch(WeatherModel.Action.SetLoading(true))

        val stateFulLoading = model.state.isLoading
        assertTrue(stateFulLoading)

    }

    @Test
    fun itShouldNotLoadForever() {
        val model = WeatherModel()

        model.dispatch(WeatherModel.Action.SetLoading(true))
        val givenWeather = WeatherForDateRange(city = City(name = "Durham"))
        model.dispatch(WeatherModel.Action.WeatherSuccess(givenWeather))

        val statefulLoading = model.state.isLoading
        assertFalse(statefulLoading)

    }

}
