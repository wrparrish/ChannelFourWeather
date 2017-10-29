package com.ruthlessprogramming.channelfourweather.mvp.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel

/**
 * Created by billyparrish on 10/28/17.
 */
interface WeatherView : MvpView {
    fun renderState(state: WeatherModel.State)
}