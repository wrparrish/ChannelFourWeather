package com.ruthlessprogramming.channelfourweather.mvp.presenter

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ruthlessprogramming.businesslogic.usecase.GetWeather
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel.Action.*
import com.ruthlessprogramming.channelfourweather.mvp.view.WeatherView
import io.reactivex.android.schedulers.AndroidSchedulers
import redux.api.Store


class WeatherPresenter(private val model: WeatherModel,
                       private val getWeather: GetWeather) : MvpBasePresenter<WeatherView>(), Store.Subscriber {
    var subscription: Store.Subscription? = null

    override fun onStateChanged() {
        view?.renderState(model.state)
    }


    override fun attachView(view: WeatherView?) {
        super.attachView(view)
        subscription = model.subscribe(this)
    }


    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        subscription?.unsubscribe()
    }


    fun getWeather() {
        model.dispatch(SetLoading(true))

        getWeather.execute(GetWeather.Request("27707"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    model.dispatch(WeatherSuccess(it.data))
                }, {
                    model.dispatch(WeatherFailure())
                })
    }

}