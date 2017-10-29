package com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ruthlessprogramming.channelfourweather.R

@EpoxyModelClass
abstract class DailyWeatherModel : EpoxyModelWithHolder<DailyWeatherModel.Holder>() {
    @EpoxyAttribute
    var dayString: String = ""
    @EpoxyAttribute
    var iconDrawable: Int = 0
    @EpoxyAttribute
    var summaryString: String = ""
    @EpoxyAttribute
    var tempString: String = ""


    override fun getDefaultLayout(): Int {
        return R.layout.weather_daily
    }

    override fun bind(holder: Holder) {


        holder.dayTextView.text = dayString
        holder.dayTempTextView.text = "$tempString °"
        holder.daySummaryTextView.text = summaryString

        holder.dayWeatherImageView.setImageDrawable(holder.dayWeatherImageView.context.getDrawable(iconDrawable))
    }

    inner class Holder : EpoxyHolder() {
        lateinit var dayTextView: TextView
        lateinit var dayTempTextView: TextView
        lateinit var daySummaryTextView: TextView
        lateinit var dayWeatherImageView: ImageView


        override fun bindView(itemView: View) {
            dayTextView = itemView.findViewById(R.id.tvDayAbbrev)
            dayTempTextView = itemView.findViewById(R.id.tvDailyTemp)
            daySummaryTextView = itemView.findViewById(R.id.tvDailySummary)
            dayWeatherImageView = itemView.findViewById(R.id.ivDailyWeather)
        }

    }
}