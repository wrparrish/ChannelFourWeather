package com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models

import android.support.design.widget.CoordinatorLayout
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.ruthlessprogramming.channelfourweather.R


@EpoxyModelClass
abstract class CurrentWeather : EpoxyModel<CoordinatorLayout>() {
    @EpoxyAttribute
    var wind: Double = 0.0
    @EpoxyAttribute
    var temp: Double = 0.0
    @EpoxyAttribute
    var iconDrawable: Int = 0
    @EpoxyAttribute
    var cityName: String = ""
    @EpoxyAttribute
    var summaryString: String = ""


    override fun getDefaultLayout(): Int {
        return R.layout.weather_current
    }


    override fun bind(view: CoordinatorLayout) {
        val currentZip = view.findViewById<TextView>(R.id.tvCurrentZip)
        val currentWeather = view.findViewById<ImageView>(R.id.ivCurrentWeather)
        val currentWind = view.findViewById<TextView>(R.id.tvWindSpeed)
        val currentTemp = view.findViewById<TextView>(R.id.tvCurrentTemp)
        val currentSummary = view.findViewById<TextView>(R.id.tvCurrentSummary)

        currentZip.text = "Current Weather For $cityName"
        currentWind.text = "${wind.toInt()} mph"
        currentTemp.text = "${temp.toInt()} °"
        currentSummary.text = summaryString

        currentWeather.setImageDrawable(view.context.getDrawable(iconDrawable))
    }
}