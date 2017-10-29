package com.ruthlessprogramming.channelfourweather

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.ruthlessprogramming.channelfourweather.common.di.Injector
import com.ruthlessprogramming.channelfourweather.mvp.model.WeatherModel
import com.ruthlessprogramming.channelfourweather.mvp.presenter.WeatherPresenter
import com.ruthlessprogramming.channelfourweather.mvp.view.WeatherView
import com.ruthlessprogramming.channelfourweather.mvp.view.epoxy.WeatherEpoxyController
import javax.inject.Inject

class MainActivity : MvpActivity<WeatherView, WeatherPresenter>(), WeatherView {
    private lateinit var controller: WeatherEpoxyController
    private lateinit var recyclerView: RecyclerView

    lateinit @Inject
    var weatherPresenter: WeatherPresenter


    override fun createPresenter(): WeatherPresenter {
        return weatherPresenter
    }

    override fun renderState(state: WeatherModel.State) {
        controller.setData(state)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.getWeather()
    }


    private fun setupRecyclerView() {
        controller = WeatherEpoxyController()
        controller.let {
            recyclerView = findViewById(R.id.recycler_view) as RecyclerView
            val layoutManager = LinearLayoutManager(this)
            it.spanCount = 1
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = it.adapter
        }
    }
}
