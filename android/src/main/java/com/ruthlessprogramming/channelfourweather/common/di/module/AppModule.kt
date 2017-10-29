package com.ruthlessprogramming.channelfourweather.common.di.module

import android.app.Application
import android.content.Context
import com.ruthlessprogramming.businesslogic.usecase.GetWeather
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel
import com.ruthlessprogramming.channelfourweather.mvp.presenter.WeatherPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    fun providesApplicationContext(): Context {
        return application.baseContext
    }

    @Provides
    @Singleton
    fun providesWeatherPresenter(getWeather: GetWeather): WeatherPresenter {
        return WeatherPresenter(WeatherModel(), getWeather)
    }

}