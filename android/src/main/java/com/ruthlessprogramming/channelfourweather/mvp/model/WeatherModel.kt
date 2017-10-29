package com.ruthlessprogramming.channelfourweather.mvp.model

import com.ruthlessprogramming.domain.City
import com.ruthlessprogramming.domain.ListItem
import com.ruthlessprogramming.domain.WeatherForDateRange
import com.ruthlessprogramming.domain.WeatherItem
import redux.api.Reducer
import redux.api.Store

class WeatherModel : StoreModel<WeatherModel.State>() {

    override fun createStore(): Store<State> {
        return redux.createStore(
                reducer(),
                State())

    }

    data class State(
            val isLoading: Boolean = false,
            val data: WeatherForDateRange = WeatherForDateRange(),
            val currentWeather: ListItem? = ListItem(),
            val currentSubWeather: WeatherItem? = WeatherItem(),
            val statusMessage: String = "",
            val currentCity: City = City()
    )


    sealed class Action {
        class SetLoading(val isLoading: Boolean) : Action()
        class WeatherSuccess(val data: WeatherForDateRange)
        class WeatherFailure
    }


    private fun reducer() = Reducer { state: State, action: Any ->
        when (action) {
            is Action.SetLoading -> state.copy(isLoading = action.isLoading)
            is Action.WeatherSuccess -> handleWeatherSuccess(state, action)
            is Action.WeatherFailure -> state.copy(isLoading = false, statusMessage = "There was a problem fetching the weather.")

            else -> state
        }
    }

    private fun handleWeatherSuccess(state: State, action: Action.WeatherSuccess): State? {
        val weatherResponse = action.data
        val currentCity = weatherResponse.city ?: City()
        val currentWeather = weatherResponse.list?.firstOrNull()
        val currentSubWeather = currentWeather?.weather?.firstOrNull()

        return state.copy(isLoading = false,
                statusMessage = "",
                data = weatherResponse,
                currentWeather = currentWeather,
                currentSubWeather = currentSubWeather,
                currentCity = currentCity)
    }

}