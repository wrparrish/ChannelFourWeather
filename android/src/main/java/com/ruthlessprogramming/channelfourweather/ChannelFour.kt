package com.ruthlessprogramming.channelfourweather

import android.app.Application
import com.ruthlessprogramming.channelfourweather.common.di.Injector
import com.ruthlessprogramming.channelfourweather.common.di.component.ChannelFourComponent
import com.ruthlessprogramming.channelfourweather.common.di.component.DaggerChannelFourComponent
import com.ruthlessprogramming.channelfourweather.common.di.module.AppModule
import com.ruthlessprogramming.channelfourweather.common.di.module.DataModule

/**
 * Created by billyparrish on 10/28/17.
 */
class ChannelFour : Application() {
    private var injector: Injector  = Injector.getInstance()

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        injector?.setComponent(createComponent())
        injector?.inject(this)
    }

    private fun createComponent(): ChannelFourComponent {
        return  DaggerChannelFourComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .build()
    }
}