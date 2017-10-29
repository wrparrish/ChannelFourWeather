package com.ruthlessprogramming.channelfourweather.mvp.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel

interface WeatherView : MvpView {
    fun renderState(state: WeatherModel.State)
}