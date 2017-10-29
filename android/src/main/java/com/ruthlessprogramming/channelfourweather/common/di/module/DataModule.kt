package com.ruthlessprogramming.channelfourweather.common.di.module

import com.google.gson.Gson
import com.ruthlessprogramming.businesslogic.data.WeatherNetworkDataSource
import com.ruthlessprogramming.channelfourweather.BuildConfig
import com.ruthlessprogramming.data.retrofit.WeatherRetrofitDataSource
import com.ruthlessprogramming.data.retrofit.service.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {
    private val TIMEOUT_MS = 10000L
    private val WEATHER_KEY = "8b3a9bc237f4a2abf35cc69a95ae60c6"
    private val WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val weatherClient = OkHttpClient.Builder()
                .readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(getCommonLogging())
                .addInterceptor { chain ->
                    val originalRequest = chain.request()
                    val originalUrl = originalRequest.url()
                    val newUrl = originalUrl.newBuilder()
                            .addQueryParameter("APPID", WEATHER_KEY)
                            .build()

                    chain.proceed(originalRequest
                            .newBuilder()
                            .url(newUrl)
                            .build())
                }
                .build()

        return weatherClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(WEATHER_BASE_URL)
                .client(httpClient)
                .build()

        return retrofit
    }

    @Singleton
    @Provides
    fun providesWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun providesWeatherNetworkDataSource(weatherService: WeatherService) :  WeatherNetworkDataSource {
        return WeatherRetrofitDataSource(weatherService)
    }

    fun getCommonLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        val isLoggingEnabled = BuildConfig.DEBUG
        val logLevel = if (isLoggingEnabled)
            HttpLoggingInterceptor.Level.BASIC
        else
            HttpLoggingInterceptor.Level.NONE
        logging.level = logLevel
        return logging
    }

}